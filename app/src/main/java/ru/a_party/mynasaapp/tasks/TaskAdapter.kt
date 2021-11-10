package ru.a_party.mynasaapp.tasks

import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ru.a_party.mynasaapp.R

val COMPLETE_TASK_ITEM_TYPE=10
val NOT_COMPLETE_TASK_ITEM_TYPE=20

class TaskAdapter(
    private var tasks: MutableList<Task>
    ): RecyclerView.Adapter<myViewHolder>(),ItemTouchHelperAdapter {


    inner class taskCompleteViewHolder(itemView: View) : myViewHolder(itemView)
    {
        override fun bind(taskItem: Task){
            itemView.findViewById<TextView>(R.id.tvCaptionTask).text = Html.fromHtml("<s>"+taskItem.name+"</s>")
        }
    }

    inner class taskViewHolder(itemView: View):myViewHolder(itemView){
        override fun bind(taskItem: Task){
            itemView.findViewById<TextView>(R.id.tvCaptionTask).text = taskItem.name
            itemView.findViewById<TextView>(R.id.tvDescriprion).text = taskItem.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        when (viewType){
            NOT_COMPLETE_TASK_ITEM_TYPE->{
                val itemView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.task_elm, parent, false)
                return taskViewHolder(itemView)
            }
            else->{
                val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.complete_task_elm, parent, false)
                return taskCompleteViewHolder(itemView)
            }
        }
    }

    fun sortList(){
        var yes=false
        //проход первый
        val list:MutableList<Task> = listOf<Task>().toMutableList()
        for(task in tasks){
            if (!task.complete){
                list.add(task)
            }
        }
        for (task in tasks){
            if (task.complete)
            {
                list.add(task)
            }
        }
        val diffResult = DiffUtil.calculateDiff(DiffUtilCallback(tasks, list))
        diffResult.dispatchUpdatesTo(this)
        tasks.clear()
        tasks.addAll(list)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        holder.bind(tasks[position])
    }

    override fun getItemViewType(position: Int): Int {
        when(tasks[position].complete){
            true->return COMPLETE_TASK_ITEM_TYPE
            false->return NOT_COMPLETE_TASK_ITEM_TYPE
        }
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        tasks.removeAt(fromPosition).apply {
            tasks.add(if (toPosition > fromPosition) toPosition - 1 else toPosition, this)
        }
        notifyItemMoved(fromPosition, toPosition)
    }

    override fun onItemDismiss(position: Int) {
        tasks.removeAt(position)
        notifyItemRemoved(position)
    }
}

