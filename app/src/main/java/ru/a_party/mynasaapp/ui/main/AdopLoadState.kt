package ru.a_party.mynasaapp.ui.main

sealed class AdopLoadState {
    object Loading:AdopLoadState()
    data class Success(val nasaData:AdopNasaData) : AdopLoadState()
    data class Failure(val t:Throwable):AdopLoadState()
}
