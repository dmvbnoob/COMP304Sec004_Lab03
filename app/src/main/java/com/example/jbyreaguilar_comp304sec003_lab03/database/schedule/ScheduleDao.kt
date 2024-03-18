package com.example.jbyreaguilar_comp304sec003_lab03.database.schedule

import Schedule
import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ScheduleDao {

    @Query("SELECT * FROM schedule ORDER BY arrival_time ASC")
    fun getAllSchedules(): Flow<List<Schedule>>

    @Query("SELECT * FROM schedule WHERE airline_name = :airlineName ORDER BY arrival_time ASC")
    fun getSchedulesByAirlineName(airlineName: String): Flow<List<Schedule>>
}
