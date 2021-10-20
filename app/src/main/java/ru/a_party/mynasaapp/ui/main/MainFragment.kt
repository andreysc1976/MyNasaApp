package ru.a_party.mynasaapp.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import com.google.android.material.textview.MaterialTextView
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.a_party.mynasaapp.API_KEY
import ru.a_party.mynasaapp.R

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View,savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.liveData.observe(viewLifecycleOwner) {
            when (it) {
                is AdopLoadState.Loading -> {
                    //тут просто идет загрузка
                }
                is AdopLoadState.Success -> {
                    view.findViewById<MaterialTextView>(R.id.materialTextView).text=it.nasaData.explanation
                    var imgView = view.findViewById<AppCompatImageView>(R.id.appCompatImageView)
                    Picasso.get().load(it.nasaData.url).into(imgView)
                }
                is AdopLoadState.Failure -> {
                    Log.e("error-Inet", it.t.toString())

                }
            }
        }
        viewModel.loadData()
    }

}