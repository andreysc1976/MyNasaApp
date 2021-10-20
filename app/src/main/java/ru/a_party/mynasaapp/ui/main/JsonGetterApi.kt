package ru.a_party.mynasaapp.ui.main

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface JsonGetterApi {
    @GET("/planetary/apod")
    fun getApod(@Query("api_key") api_key: String):Call<AdopNasaData>
}