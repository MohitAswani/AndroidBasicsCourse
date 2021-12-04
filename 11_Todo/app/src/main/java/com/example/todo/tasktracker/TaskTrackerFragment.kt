/*
 * Copyright 2019, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.todo.tasktracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.todo.R
import com.example.todo.database.TodoDatabase
import com.example.todo.databinding.FragmentTaskQualityBinding

/**
 * A fragment with buttons to record start and end times for sleep, which are saved in
 * a database. Cumulative data is displayed in a simple scrollable TextView.
 * (Because we have not learned about RecyclerView yet.)
 */
class TaskTrackerFragment : Fragment() {

    /**
     * Called when the Fragment is ready to display content to the screen.
     *
     * This function uses DataBindingUtil to inflate R.layout.fragment_sleep_quality.
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Get a reference to the binding object and inflate the fragment views.
        val binding: FragmentTaskQualityBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_task_quality, container, false)

        val application= requireNotNull(this.activity).application

        val dataSource=TodoDatabase.getInstance(application).todoDatabaseDao

        val viewModelFactory= TaskTrackerViewModelFactory(dataSource,application)

        val viewModel=ViewModelProvider(this,viewModelFactory).get(TaskTrackerViewModel::class.java)

        binding.lifecycleOwner = this

        binding.viewModel=viewModel

        viewModel.navigateToSleepQuality.observe(viewLifecycleOwner, Observer {
            task->
            task?.let {
                this.findNavController().navigate(TaskTrackerFragmentDirections.actionTaskTrackerFragmentToTodoTaskFragment(task.id))
                viewModel.doneNavigating()
            }
        })


        return binding.root
    }
}
