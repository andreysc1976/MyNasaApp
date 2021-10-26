package ru.a_party.mynasaapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.a_party.mynasaapp.API_KEY
import ru.a_party.mynasaapp.api.NetworkService

class MainViewModel : ViewModel() {
    val liveDataForObserver: MutableLiveData<AdopLoadState> = MutableLiveData()
    val liveData:LiveData<AdopLoadState> = liveDataForObserver

    fun loadData(){
        NetworkService
            .instance
            ?.getJsonApi()
            ?.getApod(API_KEY)
            ?.enqueue(object: Callback<AdopNasaData> {
                override fun onResponse(
                    call: Call<AdopNasaData>,
                    response: Response<AdopNasaData>,
                ) {
                    liveDataForObserver.postValue(response.body()?.let { AdopLoadState.Success(it) })
                }

                override fun onFailure(call: Call<AdopNasaData>, t: Throwable) {
                    liveDataForObserver.postValue(AdopLoadState.Failure(t))
                }
            })
    }
}