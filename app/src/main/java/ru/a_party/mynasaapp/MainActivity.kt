package ru.a_party.mynasaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.a_party.mynasaapp.ui.main.AdopNasaData
import ru.a_party.mynasaapp.ui.main.MainFragment
import ru.a_party.mynasaapp.ui.main.NetworkService

val API_KEY:String="rN0em65SRqhgW0KbedmHE5NU0uCcUtnQc6xqYC6V"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }

    }
}