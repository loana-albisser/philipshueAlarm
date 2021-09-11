package com.loanaalbisser.philipshueAlarm

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Checkbox
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.loanaalbisser.philipshueAlarm.hue.Light

@Composable
fun LightsList(lights: List<Light>, onCheckedChange: (Light, Boolean) -> Unit) {
    LazyColumn(
        modifier = Modifier.padding(all = 16.dp),
        reverseLayout = true
    ) {
        for (light in lights) {
            item {
                LightRow(light, onCheckedChange)
            }
        }
    }
}

@Composable
private fun LightRow(light: Light, onCheckedChange: (Light, Boolean) -> Unit) {
    Row(
        modifier = Modifier.padding(bottom = 16.dp),
    ) {
        val checkedState = remember { mutableStateOf(light.state.on) }
        Checkbox(
            modifier = Modifier.padding(end = 16.dp),
            checked = checkedState.value,
            onCheckedChange = {
                checkedState.value = it
                onCheckedChange(light, it)
            }
        )
        Text(text = light.name)

    }
}