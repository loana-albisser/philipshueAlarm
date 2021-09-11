package com.loanaalbisser.philipshueAlarm

import com.loanaalbisser.philipshueAlarm.hue.Light

interface ILightRepository {
    fun getLights(): List<Light>
    fun setLights(lights: List<Light>)
}

class LightRepository : ILightRepository {
    private var _lights = listOf<Light>()

    override fun getLights(): List<Light> {
        return _lights
    }

    override fun setLights(lights: List<Light>) {
        _lights = lights
    }
}