package com.loanaalbisser.philipshueAlarm.addAlarm

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.loanaalbisser.philipshueAlarm.LightsList

@ExperimentalMaterialApi
@Composable
fun AddAlarmScreen(viewModel: AddAlarmViewModel = viewModel()) {
    Column(modifier = Modifier.padding(8.dp)) {
        LightList(viewModel = viewModel)
        BrightnessComponent("Start Brightness")
        BrightnessComponent("End Brightness")
        WeekdayListComponent()
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
            modifier = Modifier.padding(16.dp)
        ) {
            val lights = viewModel.getLights()
            LightsList(lights, onCheckedChange = viewModel::onLightStateChanged)
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun BrightnessComponent(title: String) {
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
            Title(title)
            BrightnessSlider()
        }
    }
}

@Composable
fun Title(title: String) {
    Text(
        color = Color.White,
        text = title)
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

@Composable
fun WeekdayListComponent() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { },
        elevation = 10.dp
    ) {
        Column(
            modifier = Modifier.padding(15.dp),
        ) {
            Title("Repetition")
            WeekdayList()
        }
    }
}

@Composable
fun WeekdayList() {
    Row(modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center) {
        SingleWeekday(text = "M")
        SingleWeekday(text = "D")
        SingleWeekday(text = "M")
        SingleWeekday(text = "D")
        SingleWeekday(text = "F")
        SingleWeekday(text = "S")
        SingleWeekday(text = "S")
    }

}

@Composable
fun SingleWeekday(text: String) {
    Surface(
        modifier = Modifier
            .size(40.dp)
            .padding(4.dp),
        // border = BorderStroke(2.dp, Color.Red),
        contentColor = Color.Blue,
        elevation = 8.dp,
        color = Color.White,
        shape = CircleShape,
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = text
            )
        }
    }
}


