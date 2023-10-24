package com.example.kt_less25

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

class AddToDoFragment: Fragment() {

    private lateinit var viewModel: ToDoViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_todo_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(ToDoViewModel::class.java)
        val taskInput:EditText = view.findViewById(R.id.inputField)
        val addButton: Button = view.findViewById(R.id.todoButton)

        addButton.setOnClickListener {
            val task = taskInput.text.toString()
            viewModel.add(task)
            parentFragmentManager.popBackStack()
        }
    }
}