package com.loanaalbisser.philipshueAlarm

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import com.loanaalbisser.philipshueAlarm.hue.*
import com.loanaalbisser.philipshueAlarm.ui.theme.PhilipsHueAlarmTheme
import kotlinx.coroutines.*

class MainActivity : ComponentActivity() {
    private val viewModel by lazy { MainViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PhilipsHueAlarmTheme {
                Surface(color = MaterialTheme.colors.background) {
                    LightsList()
                }
            }
        }
    }

    @Composable
    fun OutlinedButton() {
        OutlinedButton(onClick = { /* Do something! */ }) {
            Text("Search Hue Bridge")
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        PhilipsHueAlarmTheme {
            OutlinedButton()
        }
    }

    @Preview
    @Composable
    fun LightsList() {
        val messages by viewModel.lights.observeAsState(mutableListOf())
        LazyColumn(
            modifier = Modifier.padding(all = 16.dp),
            reverseLayout = true
        ) {
            for (light in messages) {
                item {
                    LightRow(light)
                }
            }
        }

    }

    @Composable
    fun LightRow(light: Light) {
        Row(
            modifier = Modifier.padding(bottom = 16.dp),
        ) {

            val checkedState = remember { mutableStateOf(light.state.on) }
            Switch(
                modifier = Modifier.padding(end = 16.dp),
                checked = checkedState.value,
                onCheckedChange = {
                    checkedState.value = it
                    viewModel.switchLightState(light, it)
                    if (it) {
                        lifecycleScope.launch {
                            delay(1000)
                            viewModel.setLightBla(light)
                        }
                    }
                }
            )
            Text(text = light.name)

        }
    }
}
