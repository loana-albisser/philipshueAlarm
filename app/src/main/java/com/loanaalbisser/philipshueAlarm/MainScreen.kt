package com.loanaalbisser.philipshueAlarm

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.loanaalbisser.philipshueAlarm.hue.Light
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun MainScreen(navController: NavController, viewModel: MainViewModel) {
    Scaffold(
        topBar = {/**/ },
        drawerContent = {/**/ },
        bottomBar = {/**/ },
        floatingActionButton = { addAlarmFabButton(navController) },
        snackbarHost = {/**/ },
        content = {
            LightsList(viewModel)
        })
}

@Composable
fun addAlarmFabButton(navController: NavController) {
    val onClick = {
        navController.navigate(Route.ADD_ALARM_SCREEN)
    }
    FloatingActionButton(onClick = onClick) {
        Icon(Icons.Filled.Add, "Add")
    }
}

@Composable
fun LightsList(viewModel: MainViewModel) {
    val messages by viewModel.lights.observeAsState(mutableListOf())
    val test = messages
    LazyColumn(
        modifier = Modifier.padding(all = 16.dp),
        reverseLayout = true
    ) {
        for (light in messages) {
            item {
                LightRow(light, viewModel)
            }
        }
    }

}

@Composable
fun LightRow(light: Light, viewModel: MainViewModel) {
    Row(
        modifier = Modifier.padding(bottom = 16.dp),
    ) {
        val scope = rememberCoroutineScope()
        val checkedState = remember { mutableStateOf(light.state.on) }
        Switch(
            modifier = Modifier.padding(end = 16.dp),
            checked = checkedState.value,
            onCheckedChange = {
                checkedState.value = it
                viewModel.switchLightState(light, it)
                if (it) {
                    viewModel.setLightBrightness(light, 100)
                    scope.launch {
                        delay(1000)
                        viewModel.setLightBrightness(light, 60)
                    }
                }
            }
        )
        Text(text = light.name)

    }
}