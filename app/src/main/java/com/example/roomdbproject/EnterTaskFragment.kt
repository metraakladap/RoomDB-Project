package com.example.roomdbproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

class EnterTaskFragment : Fragment() {
    private lateinit var viewModel: TaskViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_enter_task, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        val editText = view.findViewById<EditText>(R.id.editText)
        view.findViewById<Button>(R.id.addButton).setOnClickListener {
            val taskName = editText.text.toString()
            if (taskName.isNotEmpty()) {
                viewModel.addData(Data(taskName = taskName))
                parentFragmentManager.popBackStack()
            }
        }
    }
}