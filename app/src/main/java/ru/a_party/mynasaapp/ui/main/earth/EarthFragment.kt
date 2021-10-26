package ru.a_party.mynasaapp.ui.main.earth

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.a_party.mynasaapp.R
import ru.a_party.mynasaapp.ui.main.MainViewModel

class EarthFragment : Fragment() {

    companion object {
        fun newInstance() = EarthFragment()
        fun getCaption() = "Земля"
    }

    private lateinit var viewModel: EarthViewModel
    private val earthAdapter by lazy {
        EarthAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.earth_fragment, container, false)
        val recyler = view.findViewById<RecyclerView>(R.id.rvEarthList)
        recyler.adapter = earthAdapter
        recyler.layoutManager= LinearLayoutManager(context)
        return view
    }

    override fun onViewCreated(view: View,savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(EarthViewModel::class.java)
        viewModel.loadData()
        viewModel.liveData.observe(viewLifecycleOwner)
        {
            when(it){
                is EarthDataLoadState.Success->{
                    earthAdapter.earthData = it.earthData
                }
                is EarthDataLoadState.Failure->{

                }
                is EarthDataLoadState.Loading ->{

                }
            }
        }
    }

}