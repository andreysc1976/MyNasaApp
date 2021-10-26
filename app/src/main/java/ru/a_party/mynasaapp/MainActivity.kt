package ru.a_party.mynasaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.Menu
import android.view.MenuItem
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout
import ru.a_party.mynasaapp.ui.main.MainFragment
import ru.a_party.mynasaapp.ui.main.MyViewPagerAdapter


val API_KEY:String="rN0em65SRqhgW0KbedmHE5NU0uCcUtnQc6xqYC6V"
val KEY_CUSTOM_THEME_CHECKED:String="theme_name_nasa"

class MainActivity : AppCompatActivity() {

    var currentTheme = "system"

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
            currentTheme=it
        }

        setContentView(R.layout.main_activity_r)



        val myViewPager = findViewById<ViewPager>(R.id.viewpager)
        myViewPager.adapter = MyViewPagerAdapter(supportFragmentManager)
        findViewById<TabLayout>(R.id.tabLayout).setupWithViewPager(myViewPager)


        myViewPager.setOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                //
            }

            override fun onPageSelected(position: Int) {

            }

            override fun onPageScrollStateChanged(state: Int) {
                //
            }

        })

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.options_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    fun selectTheme(name:String){
        if (name!=currentTheme) {
            preference.edit()
                .putString(KEY_CUSTOM_THEME_CHECKED, name)
                .apply()
            recreate()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.themesSun->{
                selectTheme("sun")
            }
            R.id.themesMars->{
                selectTheme("mars")
            }
            R.id.defaultTheme->{
                selectTheme("system")
            }
        }
        return super.onOptionsItemSelected(item)
    }
}