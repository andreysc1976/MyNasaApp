package ru.a_party.mynasaapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val liveDataForObserver: LiveData<AdopLoadState> = MutableLiveData<AdopLoadState>()

}