package com.example.todo.todotasks

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.todo.R
import com.example.todo.database.TodoDatabase
import com.example.todo.databinding.TodoTaskFragmentBinding

class TodoTaskFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding : TodoTaskFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.todo_task_fragment, container, false)

        val application= requireNotNull(this.activity).application

        val dataSource=TodoDatabase.getInstance(application).todoDatabaseDao

        val viewModelFactory=TodoTaskViewModelFactory(dataSource,application)

        val todoTaskViewModel=ViewModelProvider(this,viewModelFactory).get(TodoTaskViewModel::class.java)

        binding.lifecycleOwner = this

        binding.viewModel=todoTaskViewModel

        binding.taskDoneButton.isEnabled=false

        binding.addTaskButton.setOnClickListener{
            if(binding.taskName.text.toString().trim().isEmpty())
            {
                Toast.makeText(context,"Please enter the task name",Toast.LENGTH_SHORT).show()
            }
            else
            {
                todoTaskViewModel.onAddTask(binding.taskName.text.toString().trim())
                binding.taskName.text.clear()
                binding.addTaskButton.isEnabled=false
                binding.taskDoneButton.isEnabled=true
            }
        }

        binding.taskDoneButton.setOnClickListener {
            todoTaskViewModel.onTaskDone()
            binding.addTaskButton.isEnabled=true
            binding.taskDoneButton.isEnabled=false
        }
        return binding.root
    }

}