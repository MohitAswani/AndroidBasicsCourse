package com.example.todo.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


@Dao
interface TodoDatabaseDao{

    @Insert
    suspend fun insert(todoTask: TodoTask)

    @Update
    suspend fun update(todoTask: TodoTask)

    @Query("SELECT * FROM todo_list WHERE id=:key ")
    suspend fun getTask(key:Long) :TodoTask

    @Query("SELECT * FROM todo_list ORDER BY id DESC")
    fun getAllTask() : LiveData<List<TodoTask>>

    @Query("SELECT * FROM todo_list ORDER BY id DESC LIMIT 1")
    suspend fun getCurrentTask() : TodoTask?

    @Query("DELETE FROM todo_list")
    suspend fun clear()
}