/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.loanaalbisser.philipshueAlarm

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = Route.MAIN_SCREEN) {
        composable(route = Route.MAIN_SCREEN) {
            val viewModel = MainViewModel()
            MainScreen(navController, viewModel)
        }
        composable(route = Route.ADD_ALARM_SCREEN) {
            val viewModel = AddAlarmViewModel()
            AddAlarmScreen(viewModel)
        }
    }
}

class Route {
    companion object {
        const val MAIN_SCREEN = "main_screen"
        const val ADD_ALARM_SCREEN = "add_alarm_screen"
    }
}
