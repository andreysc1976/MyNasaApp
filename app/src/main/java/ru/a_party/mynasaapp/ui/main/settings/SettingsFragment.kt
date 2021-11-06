package ru.a_party.mynasaapp.ui.main.settings


import android.os.Bundle
import android.transition.ChangeBounds
import android.transition.TransitionManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnticipateInterpolator
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import ru.a_party.mynasaapp.MainActivity
import ru.a_party.mynasaapp.R

class SettingsFragment : Fragment() {

    companion object {
        fun newInstance() = SettingsFragment()
        fun getCaption() = "Настройки"
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.settings_fragment, container, false)

        (requireActivity() as MainActivity)?.let {
            when(it.currentTheme){
                "sun"->{view.findViewById<Chip>(R.id.chip4).isChecked=true}
                "mars"->{view.findViewById<Chip>(R.id.chip5).isChecked=true}
                else ->{view.findViewById<Chip>(R.id.chip6).isChecked=true}
            }
        }

        view.findViewById<ChipGroup>(R.id.chipGroup).setOnCheckedChangeListener(object:
            ChipGroup.OnCheckedChangeListener{
            override fun onCheckedChanged(group: ChipGroup?, checkedId: Int) {
                (requireActivity() as MainActivity)?.let {
                    when (checkedId) {
                        R.id.chip4 -> {it.selectTheme("sun")}
                        R.id.chip5 -> it.selectTheme("mars")
                        else -> {it.selectTheme("system")}
                    }
                }
            }
        })




        view.findViewById<Button>(R.id.button).setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                show_about(view)
            }

        })

        return view
    }


    private fun show_about(view:View) {
        val constraitSet = ConstraintSet()
        constraitSet.clone(activity,R.layout.settings_fragment_end)

        val transition = ChangeBounds()
        transition.interpolator = AnticipateInterpolator(1.0f)
        transition.duration=1000

        val constraintLayout = view.findViewById<ConstraintLayout>(R.id.frameLayoutSettings)

        TransitionManager.beginDelayedTransition(constraintLayout,transition)
        constraitSet.applyTo(constraintLayout)
    }
}