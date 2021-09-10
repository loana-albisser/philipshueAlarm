package com.loanaalbisser.philipshueAlarm

import com.loanaalbisser.philipshueAlarm.hue.Bridge
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class NUPnPDiscovery : IBridgeDiscovery {
    private val baseUrl: String = "https://discovery.meethue.com/"
    /* private val nUPnPService: NUPnPService by inject(NUPnPService::class.java) {
        parametersOf(
            baseUrl
        )
    } */
    private val nUPnPService: NUPnPService =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NUPnPService::class.java)

    override suspend fun getBridges(): List<Bridge> {
        return withContext(Dispatchers.IO) {
            nUPnPService.getBridgeAsync()
        }
    }
}

interface NUPnPService {
    @GET("/")
    suspend fun getBridgeAsync(): List<Bridge>
}

interface IBridgeDiscovery {
    suspend fun getBridges():List<Bridge>
}
