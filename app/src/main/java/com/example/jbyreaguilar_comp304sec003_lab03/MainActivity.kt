package com.example.jbyreaguilar_comp304sec003_lab03

import com.example.jbyreaguilar_comp304sec003_lab03.database.schedule.Schedule
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.lifecycleScope
import com.example.jbyreaguilar_comp304sec003_lab03.database.AppDatabase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var scheduleAdapter: MyCustomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Air Schedule"

        val listView = findViewById<ListView>(R.id.main_listview)
        scheduleAdapter = MyCustomAdapter(this@MainActivity, emptyList())
        listView.adapter = scheduleAdapter

        listView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            // Open new activity here using the clicked item's details
            val schedule = scheduleAdapter.getItem(position) as Schedule // Cast to Schedule
            val intent = Intent(this@MainActivity, DetailedScheduleActivity::class.java).apply {
                putExtra("airline_name", schedule.airlineName)
                putExtra("arrival_time", schedule.arrivalTime)
                putExtra("terminal_number", schedule.terminal)
                putExtra("status", schedule.status)
            }
            startActivity(intent)
        }

        loadData()
    }

    private fun loadData() {
        val dao = AppDatabase.getDatabase(this).scheduleDao()
        lifecycleScope.launch {
            dao.getAllSchedules().collect { schedules ->
                scheduleAdapter.updateData(schedules)
            }
        }
    }

    private class MyCustomAdapter(private val context: Context, private var data: List<Schedule>) : BaseAdapter() {

        fun updateData(newData: List<Schedule>) {
            data = newData
            notifyDataSetChanged()
        }

        override fun getCount(): Int = data.size

        override fun getItem(position: Int): Any = data[position]

        override fun getItemId(position: Int): Long = position.toLong()

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val layoutInflater = LayoutInflater.from(context)
            val rowMain = layoutInflater.inflate(R.layout.row_main, parent, false)

            val schedule = data[position]

            rowMain.findViewById<TextView>(R.id.airLineName).text = schedule.airlineName
            rowMain.findViewById<TextView>(R.id.arrivalTime).text = schedule.arrivalTime
            rowMain.findViewById<TextView>(R.id.terminalNumber).text = schedule.terminal

            return rowMain
        }
    }
}