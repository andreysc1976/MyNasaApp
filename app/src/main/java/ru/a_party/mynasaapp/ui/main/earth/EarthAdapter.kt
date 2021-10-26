package ru.a_party.mynasaapp.ui.main.earth

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import ru.a_party.mynasaapp.R
import ru.a_party.mynasaapp.ui.main.earth.EarthAdapter.ViewHolder
import ru.a_party.mynasaapp.ui.main.earth.data.EarthData
import ru.a_party.mynasaapp.ui.main.earth.data.EarthDataItem
import java.lang.Exception

class EarthAdapter: RecyclerView.Adapter<ViewHolder>() {
    var earthData: EarthData?=null
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(earthDataItem: EarthDataItem) {
            itemView.findViewById<TextView>(R.id.tvCaption).text = earthDataItem.caption
            itemView.findViewById<TextView>(R.id.tvDate).text = earthDataItem.date
            val year = earthDataItem.identifier.chunked(4)[0]
            var sp = earthDataItem.identifier.chunked(4)[1].chunked(2)
            val url:String="https://epic.gsfc.nasa.gov/archive/natural/${year}/${sp[0]}/${sp[1]}/png/${earthDataItem.image}.png"
            Picasso.get().load(url).fit().centerCrop().into(itemView.findViewById<ImageView>(R.id.imageViewEarthPic))
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.earth_fragment_element, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        earthData?.let{
            holder.bind(it[position])
        }
    }



    override fun getItemCount(): Int {
        return earthData?.size ?:0
    }
}