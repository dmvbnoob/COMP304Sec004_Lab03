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

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val airlineName = intent.getStringExtra("airline_name") ?: "N/A"

        supportActionBar?.title = airlineName

        val arrivalTime = intent.getStringExtra("arrival_time") ?: "N/A"
        val terminalNumber = intent.getStringExtra("terminal_number") ?: "N/A"
        val status = intent.getStringExtra("status") ?: "N/A"

        val arrivalTimeTextView = findViewById<TextView>(R.id.arrival_time)
        val terminalNumberTextView = findViewById<TextView>(R.id.terminal_number)
        val statusTextView = findViewById<TextView>(R.id.status_text)

        arrivalTimeTextView.text = arrivalTime
        terminalNumberTextView.text = terminalNumber
        statusTextView.text = status
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