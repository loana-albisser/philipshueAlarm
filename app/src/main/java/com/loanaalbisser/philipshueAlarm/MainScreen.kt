package com.loanaalbisser.philipshueAlarm

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.loanaalbisser.philipshueAlarm.hue.Light

@Composable
fun MainScreen(navController: NavController, viewModel: MainViewModel) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val test = navBackStackEntry
    Scaffold(
        topBar = { /**/ },
        drawerContent = {/**/ },
        bottomBar = {/**/ },
        floatingActionButton = { AddAlarmFabButton(navController) },
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
fun AddAlarmFabButton(navController: NavController) {
    val onClick = {
        navController.navigate(Route.ADD_ALARM_SCREEN)
    }
    FloatingActionButton(onClick = onClick) {
        Icon(Icons.Filled.Add, "Add")
    }
}


