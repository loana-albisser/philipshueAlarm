package com.loanaalbisser.philipshueAlarm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loanaalbisser.philipshueAlarm.hue.BridgeController
import com.loanaalbisser.philipshueAlarm.hue.Light
import com.loanaalbisser.philipshueAlarm.hue.LinkButtonNotPressedException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    val mainDispatcher: CoroutineDispatcher = Dispatchers.Main
) : ViewModel() {
    private lateinit var token: String
    private lateinit var bridgeIp: String
    private val brideManager by lazy { BridgeManager()}
    private val lightController by lazy { BridgeController(bridgeIp) }

    private val _lights = MutableLiveData<List<Light>>()
    val lights: LiveData<List<Light>>
        get() = _lights

    init {
        initBridgeList()
    }

    private fun initBridgeList() {
        CoroutineScope(Dispatchers.Main).launch {
            val bridgeList = brideManager.getBridgeList()
            bridgeIp = bridgeList.first().internalIpAddress

            fetchLightList()
        }
    }

    private suspend fun tryGetToken(): String? {
        var linkButtonPressed = false
        var token: String? = null
        while (!linkButtonPressed) {
            try {
                token = lightController.getToken()
                linkButtonPressed = true
            } catch (e: LinkButtonNotPressedException) {
                Log.e("Bla", e.message.orEmpty())
            }
        }
        return token
    }

    private fun fetchLightList() {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                /* val newToken = tryGetToken()
                if (newToken != null) {
                    token = newToken
                }*/
                token = "2EM4xuxzuac9ejxILOYi8f4E2ieESILBmJC2aRr5"
                _lights.value = lightController.getLights(token).filter { light -> light.state.reachable }

            } catch (exception: Exception) {
                exception.printStackTrace()
            }

        }
    }

    fun switchLightState(light: Light, on: Boolean) {
        if (on) {
            turnOnLight(light)
        } else {
            turnOffLight(light)
        }
    }

    fun setLightBla(light: Light) {
        viewModelScope.launch(mainDispatcher) {
            lightController.changeHSVColor(token, light.id, 60, 100, light.state.hue)
        }
    }

    private fun turnOnLight(light: Light) {
        viewModelScope.launch(mainDispatcher) {
            lightController.turnOn(token, light.id, true)
        }
    }

    private fun turnOffLight(light: Light) {
        viewModelScope.launch(mainDispatcher) {
            lightController.turnOn(token, light.id, false)
        }
    }
}