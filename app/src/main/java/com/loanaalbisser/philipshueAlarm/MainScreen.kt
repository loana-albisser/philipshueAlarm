package com.loanaalbisser.philipshueAlarm

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavController
import com.loanaalbisser.philipshueAlarm.hue.Light

@Composable
fun MainScreen(navController: NavController, viewModel: MainViewModel) {
    Scaffold(
        topBar = {/**/ },
        drawerContent = {/**/ },
        bottomBar = {/**/ },
        floatingActionButton = { addAlarmFabButton(navController) },
        snackbarHost = {/**/ },
        content = {
            MainLightList(viewModel)
        })
}

@Composable
fun MainLightList(viewModel: MainViewModel) {
    val lights: List<Light> by viewModel.lights.observeAsState(mutableListOf())
    LightsList(lights, onCheckedChange = viewModel::onLightStateChanged)
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

