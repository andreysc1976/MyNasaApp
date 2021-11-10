package ru.a_party.mynasaapp.tasks

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ru.a_party.mynasaapp.R
import java.util.*

class TasksFragment : Fragment() {

    private var isNewList = false
    private lateinit var itemTouchHelper: ItemTouchHelper
    private lateinit var adapter: TaskAdapter

    companion object {
        fun newInstance() = TasksFragment()
    }

    private lateinit var viewModel: TasksViewModel

    fun demoElement():List<Task> {
        return listOf(
            Task(false,"Task 1","Задачка о том о сем", Date()),
            Task(false,"Task 2","Задачка о том о сем", Date()),
            Task(true,"Task 5","Завешенная задачка о том о сем", Date()),
            Task(false,"Task 3","Почти завершенная задачка о том о сем", Date()),
            Task(true,"Task 4","Завешенная задачка о том о сем", Date())
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.tasks_fragment, container, false)
        val recycler = view.findViewById<RecyclerView>(R.id.recyclerTasks)

        adapter = TaskAdapter(demoElement().toMutableList())

        recycler.adapter = adapter
        recycler.layoutManager= LinearLayoutManager(context)

        itemTouchHelper = ItemTouchHelper(ItemTouchHelperCallback(adapter))
        itemTouchHelper.attachToRecyclerView(recycler)



        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(TasksViewModel::class.java)

        view.findViewById<FloatingActionButton>(R.id.btnSort).setOnClickListener {adapter.sortList()}
    }

}