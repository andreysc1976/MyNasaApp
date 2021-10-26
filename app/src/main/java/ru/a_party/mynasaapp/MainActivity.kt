package ru.a_party.mynasaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.a_party.mynasaapp.ui.main.AdopNasaData
import ru.a_party.mynasaapp.ui.main.MainFragment
import ru.a_party.mynasaapp.ui.main.NetworkService

val API_KEY:String="rN0em65SRqhgW0KbedmHE5NU0uCcUtnQc6xqYC6V"
val KEY_CUSTOM_THEME_CHECKED:String="theme_name_nasa"

class MainActivity : AppCompatActivity() {

    private val preference by lazy {
        PreferenceManager.getDefaultSharedPreferences(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        preference?.getString(KEY_CUSTOM_THEME_CHECKED,null)?.let{
            when (it){
                "mars"->{
                    setTheme(R.style.Theme_Mars)
                }
                "sun"->{
                    setTheme(R.style.Theme_Sun)
                }
                "system"->{
                    //все само отработает
                }
            }
        }

        setContentView(R.layout.main_activity)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.options_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.themesSun->{
                preference.edit()
                    .putString(KEY_CUSTOM_THEME_CHECKED, "sun")
                    .apply()
                recreate()
            }
            R.id.themesMars->{
                preference.edit()
                    .putString(KEY_CUSTOM_THEME_CHECKED, "mars")
                    .apply()
                recreate()
            }
            R.id.defaultTheme->{
                preference.edit()
                    .putString(KEY_CUSTOM_THEME_CHECKED, "system")
                    .apply()
                recreate()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}