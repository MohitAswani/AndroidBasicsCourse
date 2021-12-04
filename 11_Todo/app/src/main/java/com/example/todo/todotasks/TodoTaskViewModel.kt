package com.example.todo.todotasks

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.example.todo.database.TodoDatabaseDao
import com.example.todo.database.TodoTask
import com.example.todo.formatTasks
import kotlinx.coroutines.launch

class TodoTaskViewModel (private val database: TodoDatabaseDao, application: Application) : AndroidViewModel(application) {
    private var currentTask=MutableLiveData<TodoTask?>()

    private val tasks=database.getAllTask()

    val taskStrings=Transformations.map(tasks){ tasks->
        formatTasks(tasks,application.resources)
    }

    init {
        initializeCurrentTask()
    }

    private fun initializeCurrentTask()
    {
//        CoroutineScope(IO).launch {
//
//        }  one way of starting a coroutine
        viewModelScope.launch {
            currentTask.value=getCurrentTaskFromDB()
        }
    }

    private suspend fun getCurrentTaskFromDB():TodoTask?{
        var task:TodoTask?=database.getCurrentTask()
        if(task?.endTime != task?.startTime)
        {
            task=null
        }
        return task
    }

    fun onAddTask(task_name:String){
        viewModelScope.launch {
            val task=TodoTask(taskName = task_name)
            insert(task)
            currentTask.value=getCurrentTaskFromDB()
        }
    }

    private suspend fun insert(task:TodoTask){
        database.insert(task)
    }

    fun onTaskDone(){
        viewModelScope.launch {
            val oldTask=currentTask.value ?:return@launch
            oldTask.endTime=System.currentTimeMillis()
            update(oldTask)
        }
    }

    private suspend fun update(task:TodoTask)
    {
        database.update(task)
    }

    fun onClear(){
        viewModelScope.launch {
            clear()
            currentTask.value=null
        }
    }

    private suspend fun clear(){
        database.clear()
    }

}