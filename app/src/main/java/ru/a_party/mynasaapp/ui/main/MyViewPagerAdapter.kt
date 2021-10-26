package ru.a_party.mynasaapp.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import ru.a_party.mynasaapp.ui.main.earth.EarthFragment
import ru.a_party.mynasaapp.ui.main.settings.SettingsFragment

class MyViewPagerAdapter(fragmentManager: FragmentManager): FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val myFragments = arrayOf(MainFragment(), EarthFragment(), SettingsFragment())

    override fun getCount(): Int {
        return 3
    }

    override fun getItem(position: Int): Fragment {
        return myFragments[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            1 -> return "Земля"
            2 -> return "Настройки"
            else -> return "Картинка дня"
        }
    }
}