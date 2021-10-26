package ru.a_party.mynasaapp.ui.main.earth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.a_party.mynasaapp.API_KEY
import ru.a_party.mynasaapp.api.NetworkService
import ru.a_party.mynasaapp.ui.main.AdopLoadState
import ru.a_party.mynasaapp.ui.main.earth.data.EarthData

class EarthViewModel: ViewModel() {
    val liveDataForObserver: MutableLiveData<EarthDataLoadState> = MutableLiveData()
    val liveData: LiveData<EarthDataLoadState> = liveDataForObserver

    fun loadData(){
        NetworkService
            .instance
            ?.getJsonApi()
            ?.getEpicNatural(API_KEY)
            ?.enqueue(object: Callback<EarthData> {
                override fun onResponse(call: Call<EarthData>, response: Response<EarthData>) {
                    liveDataForObserver.postValue(response.body()?.let { EarthDataLoadState.Success(it) })
                }

                override fun onFailure(call: Call<EarthData>, t: Throwable) {
                    liveDataForObserver.postValue(EarthDataLoadState.Failure(t))
                }
            })
    }
}