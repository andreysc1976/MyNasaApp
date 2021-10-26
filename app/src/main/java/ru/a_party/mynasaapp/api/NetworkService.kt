package ru.a_party.mynasaapp.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class NetworkService private constructor() {
    private val mRetrofit: Retrofit
    private val API_KEY:String="rN0em65SRqhgW0KbedmHE5NU0uCcUtnQc6xqYC6V"

    companion object {
        private var mInstance: NetworkService? = null
        private const val BASE_URL = "https://api.nasa.gov"
        val instance: NetworkService?
            get() {
                if (mInstance == null) {
                    mInstance = NetworkService()
                }
                return mInstance
            }
    }

    init {
        mRetrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getJsonApi(): JsonGetterApi {
        return mRetrofit.create(JsonGetterApi::class.java)
    }
}