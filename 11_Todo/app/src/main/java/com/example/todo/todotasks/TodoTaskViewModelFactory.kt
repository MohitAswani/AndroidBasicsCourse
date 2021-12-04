package com.example.todo.todotasks

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.todo.database.TodoDatabase
import com.example.todo.database.TodoDatabaseDao

class TodoTaskViewModelFactory(
    private val dataSource: TodoDatabaseDao,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TodoTaskViewModel::class.java)) {
            return TodoTaskViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
