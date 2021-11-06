package ru.a_party.mynasaapp.ui.main

import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import android.view.*
import android.webkit.WebView
import android.widget.VideoView
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.AppCompatImageView
import androidx.test.platform.app.InstrumentationRegistry
import com.google.android.material.textview.MaterialTextView
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.a_party.mynasaapp.API_KEY
import ru.a_party.mynasaapp.R
import android.content.Intent
import androidx.constraintlayout.motion.widget.MotionLayout
import java.lang.Exception


class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
        fun getCaption() = "Картинка дня"
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment_start_cl, container, false)
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
                    //var videoView = view.findViewById<WebView>(R.id.webView2)
                    var motion = view.findViewById<MotionLayout>(R.id.motion)
                    if (it.nasaData.media_type!="video") {
                        Picasso.get().load(it.nasaData.url).into(imgView, object: com.squareup.picasso.Callback {
                            override fun onSuccess() {
                                motion.transitionToEnd()
                            }

                            override fun onError(e: Exception?) {

                            }

                        })
                    } else {
                        motion.transitionToEnd()
                        imgView.setImageResource(R.drawable.ic_video_pic_foreground)
                        imgView.setOnClickListener {img->
                            val address = Uri.parse(it.nasaData.url)
                            val openlink = Intent(Intent.ACTION_VIEW, address)
                            startActivity(Intent.createChooser(openlink, "Browser"))
                        }
                    }
                }
                is AdopLoadState.Failure -> {
                    Log.e("error-Inet", it.t.toString())

                }
            }
        }
        viewModel.loadData()
    }
}