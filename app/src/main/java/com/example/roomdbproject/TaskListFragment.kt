package com.example.roomdbproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class TaskListFragment : Fragment() {
    private lateinit var viewModel: TaskViewModel
    private lateinit var adapter: TaskAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_task_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        adapter = TaskAdapter { task ->
            viewModel.deleteData(task)
        }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        view.findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, EnterTaskFragment())
                .addToBackStack(null)
                .commit()
        }

        viewModel.listState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is TaskViewModel.ListState.EmptyList -> {
                }
                is TaskViewModel.ListState.UpdatedList -> {
                    adapter.submitList(state.list)
                }
            }
        }
    }
}