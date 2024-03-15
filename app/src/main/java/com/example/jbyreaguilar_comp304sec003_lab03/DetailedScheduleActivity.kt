package com.example.jbyreaguilar_comp304sec003_lab03

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.widget.Toolbar

class DetailedScheduleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_schedule)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Enable the Up button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Get the airline name from the intent extras
        val airlineName = intent.getStringExtra("airline_name")

        // Set the airline name as the title of the Toolbar
        supportActionBar?.title = airlineName

        // Get Arrival Time and Terminal Number from intent extras
        val arrivalTime = intent.getStringExtra("arrival_time")
        val terminalNumber = intent.getStringExtra("terminal_number")

        // Initialize TextViews
        val arrivalTimeTextView = findViewById<TextView>(R.id.arrival_time)
        val terminalNumberTextView = findViewById<TextView>(R.id.terminal_number)

        // Set values to TextViews
        arrivalTimeTextView.text = arrivalTime
        terminalNumberTextView.text = terminalNumber
    }

    // Handle clicks on the Up button
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                // Respond to the action bar's Up button click
                onBackPressed() // or finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}