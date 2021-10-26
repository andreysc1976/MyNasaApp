package ru.a_party.mynasaapp.ui.main.earth

import ru.a_party.mynasaapp.ui.main.earth.data.EarthData

sealed class EarthDataLoadState{
    object Loading: EarthDataLoadState()
    data class Success(val earthData: EarthData) : EarthDataLoadState()
    data class Failure(val t:Throwable): EarthDataLoadState()
}
