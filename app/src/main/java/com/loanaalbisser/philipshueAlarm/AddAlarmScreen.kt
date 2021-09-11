package com.loanaalbisser.philipshueAlarm

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AddAlarmScreen(viewModel: AddAlarmViewModel) {
    Text(
        modifier = Modifier.padding(all = 64.dp),
        color = Color.White,
        text = "Add Alarm")
}

