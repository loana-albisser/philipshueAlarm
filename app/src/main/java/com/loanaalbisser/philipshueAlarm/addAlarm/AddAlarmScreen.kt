package com.loanaalbisser.philipshueAlarm.addAlarm

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.loanaalbisser.philipshueAlarm.LightsList
import com.loanaalbisser.philipshueAlarm.hue.Light

@ExperimentalMaterialApi
@Composable
fun AddAlarmScreen(viewModel: AddAlarmViewModel = viewModel()) {
    Column(modifier = Modifier.padding(8.dp)) {
        LightList(viewModel = viewModel)
        BrightnessComponent()
    }

}

@Composable
fun LightList(viewModel: AddAlarmViewModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { },
        elevation = 10.dp
    ) {
        Column(
            modifier = Modifier.padding(15.dp)
        ) {
            val lights = viewModel.getLights()
            LightsList(lights, onCheckedChange = viewModel::onLightStateChanged)
        }
    }

}

@ExperimentalMaterialApi
@Composable
fun BrightnessComponent() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { },
        elevation = 10.dp
    ) {
        Column(
            modifier = Modifier.padding(15.dp)
        ) {
            BrightnessTitle()
            BrightnessSlider()
        }
    }
}

@Composable
fun BrightnessTitle() {
    Text(
        color = Color.White,
        text = "Brightness")
}


@ExperimentalMaterialApi
@Composable
fun BrightnessSlider() {
    var sliderPosition by remember { mutableStateOf(0f..100f) }
    Text(text = sliderPosition.toString())
    RangeSlider(
        // steps = 5,
        values = sliderPosition,
        onValueChange = { sliderPosition = it },
        valueRange = 0f..100f,
        onValueChangeFinished = {
            // launch some business logic update with the state you hold
            // viewModel.updateSelectedSliderValue(sliderPosition)
        },
        colors = SliderDefaults.colors(
            thumbColor = MaterialTheme.colors.secondary,
            activeTrackColor = MaterialTheme.colors.secondary
        )
    )
}


