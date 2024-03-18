package com.example.jbyreaguilar_comp304sec003_lab03.database.schedule

import Schedule
import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ScheduleDao {

    @Query("SELECT * FROM schedule ORDER BY arrivalTime ASC")
    fun getAllSchedules(): Flow<List<Schedule>>

    @Query("SELECT * FROM schedule WHERE airlineName = :airlineName ORDER BY arrivalTime ASC")
    fun getSchedulesByAirlineName(airlineName: String): Flow<List<Schedule>>
}
