
package com.example.sleepqualitytracker.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * This data class represents every tuple present in the database table
 */
@Entity(tableName = "sleep_quality_data")
data class SleepNight(
    @PrimaryKey(autoGenerate = true)
    var id:Long=0L,
    @ColumnInfo(name = "start_time_millis")
    var startTime:Long=System.currentTimeMillis(),
    @ColumnInfo(name = "end_time_millis")
    var endTime:Long=startTime,
    @ColumnInfo(name = "sleep_quality")
    var sleepQuality:Int=-1
)