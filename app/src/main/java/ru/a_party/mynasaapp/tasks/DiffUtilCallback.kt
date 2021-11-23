package ru.a_party.mynasaapp.tasks

import androidx.recyclerview.widget.DiffUtil
import geekbarains.material.ui.recycler.Change

class DiffUtilCallback(private var oldItems: List<Task>,
                       private var newItems: List<Task>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldItems.size

    override fun getNewListSize(): Int = newItems.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldItems[oldItemPosition].name == newItems[newItemPosition].name //конечно не правильно делать ключем name, но предположим что он у меня уникальный))

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldItems[oldItemPosition].description == newItems[newItemPosition].description

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        val oldItem = oldItems[oldItemPosition]
        val newItem = newItems[newItemPosition]

        return Change(
            oldItem,
            newItem
        )
    }
}