package ru.a_party.mynasaapp.tasks

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class myViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
{
    abstract fun bind(taskItem: Task)
}