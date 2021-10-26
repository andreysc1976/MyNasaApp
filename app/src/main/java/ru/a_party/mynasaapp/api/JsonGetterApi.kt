package ru.a_party.mynasaapp.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import ru.a_party.mynasaapp.ui.main.AdopNasaData
import ru.a_party.mynasaapp.ui.main.earth.data.EarthData

interface JsonGetterApi {
    @GET("/planetary/apod")
    fun getApod(@Query("api_key") api_key: String):Call<AdopNasaData>

    @GET("/EPIC/api/natural")
    fun getEpicNatural(@Query("api_key") api_key: String):Call<EarthData>
}