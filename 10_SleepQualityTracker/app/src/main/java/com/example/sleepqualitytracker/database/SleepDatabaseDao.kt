
package com.example.sleepqualitytracker.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface SleepDatabaseDao{
    @Insert
    suspend fun insert(night: SleepNight)

    @Update
    suspend fun update(night: SleepNight)

    @Query("SELECT * FROM sleep_quality_data WHERE id=:key ")
    suspend fun getNight(key:Long):SleepNight?

    @Query("SELECT * FROM sleep_quality_data ORDER BY id DESC LIMIT 1")
    suspend fun getTonight(): SleepNight

    @Query("SELECT * FROM sleep_quality_data ORDER BY id")
    fun allNights():LiveData<List<SleepNight>>

    @Query("DELETE FROM sleep_quality_data")
    suspend fun clear()
}