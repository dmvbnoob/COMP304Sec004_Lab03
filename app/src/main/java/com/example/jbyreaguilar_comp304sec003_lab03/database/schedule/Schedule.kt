package com.example.jbyreaguilar_comp304sec003_lab03.database.schedule

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "schedule")
data class Schedule(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val airlineName: String,
    val arrivalTime: String,
    val terminal: String,
    val status: String
)
