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

import android.app.Application
import androidx.lifecycle.*
import com.example.todo.database.TodoDatabaseDao
import com.example.todo.database.TodoTask
import com.example.todo.formatTasks
import kotlinx.coroutines.launch

/**
 * ViewModel for SleepTrackerFragment.
 */
class TaskTrackerViewModel(
        val database: TodoDatabaseDao,
        application: Application) : AndroidViewModel(application) {

        private val tonight=MutableLiveData<TodoTask?>()

        private val tasks=database.getAllTask()

        val tasksString=Transformations.map(tasks){ tasks->
                formatTasks(tasks,application.resources)
        }

        private val _navigateToSleepQuality=MutableLiveData<TodoTask?>()
        val navigateToSleepQuality:LiveData<TodoTask?>
        get()=_navigateToSleepQuality

        init {
            initTonight()
        }

        private fun initTonight(){
                viewModelScope.launch {
                        tonight.value=getTonightFromDatabase()
                }
        }

        private suspend fun getTonightFromDatabase():TodoTask?
        {
                var night:TodoTask?=database.getCurrentTask()
                if(night?.startTime!=night?.endTime)
                {
                        night=null
                }
                return night
        }

        fun onStartTracking(){
                viewModelScope.launch {
                        val newNight=TodoTask()
                        insert(newNight)
                        tonight.value=getTonightFromDatabase()
                }
        }

        private suspend fun insert(night:TodoTask)
        {
                database.insert(night)
        }

        fun onStopTracking(){
                viewModelScope.launch {
                        val oldNight=tonight.value?:return@launch
                        oldNight.endTime=System.currentTimeMillis()
                        update(oldNight)
                        _navigateToSleepQuality.value=oldNight
                }
        }

        private suspend fun update(night: TodoTask)
        {
                database.update(night)
        }

        fun onClear(){
                viewModelScope.launch {
                        clear()
                        tonight.value=null
                }
        }

        private suspend fun clear(){
                database.clear()
        }

        fun doneNavigating(){
                _navigateToSleepQuality.value=null
        }
}

