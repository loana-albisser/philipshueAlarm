package com.loanaalbisser.philipshueAlarm.addAlarm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loanaalbisser.philipshueAlarm.ILightRepository
import com.loanaalbisser.philipshueAlarm.hue.Light
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AddAlarmViewModel(private val lightRepository: ILightRepository): ViewModel(){
    fun getLights(): List<Light> {
        return lightRepository.getLights()
    }

    fun onLightStateChanged(light: Light, on: Boolean) {
        val test = ""
    }
}