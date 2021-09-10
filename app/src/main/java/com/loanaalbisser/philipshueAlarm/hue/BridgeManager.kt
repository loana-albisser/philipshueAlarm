package com.loanaalbisser.philipshueAlarm

import com.loanaalbisser.philipshueAlarm.hue.Bridge

class BridgeManager : IBridgeManager {
    private val nUpnpDiscovery: NUPnPDiscovery = NUPnPDiscovery()

    override suspend fun getBridgeList(): List<Bridge> {
        return nUpnpDiscovery.getBridges()
    }
}


interface IBridgeManager {
    suspend fun getBridgeList(): List<Bridge>
}
