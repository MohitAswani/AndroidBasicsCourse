package com.example.todo.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_list")
data class TodoTask(
    @PrimaryKey(autoGenerate = true)
    var id:Long=0L,
    @ColumnInfo(name = "task_name")
    var taskName:String="AA",
    @ColumnInfo(name = "start_time_millis")
    var startTime:Long=System.currentTimeMillis(),
    @ColumnInfo(name = "end_time_millis")
    var endTime:Long=startTime,
    @ColumnInfo(name = "satisfaction_rating")
    var satisfaction:Long=-1L
)