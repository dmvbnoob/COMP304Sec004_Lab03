package com.example.jbyreaguilar_comp304sec003_lab03

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

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Air Schedule"

        val listView = findViewById<ListView>(R.id.main_listview)
        listView.adapter = MyCustomAdapter()

        listView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            // Open new activity here
            val intent = Intent(this@MainActivity, DetailedScheduleActivity::class.java)
            intent.putExtra("airline_name", names[position])
            intent.putExtra("arrival_time", time[position])
            intent.putExtra("terminal_number", "T${position + 1}")
            startActivity(intent)
        }
    }

    private class MyCustomAdapter : BaseAdapter() {

        //Responsible for how many rows in my list
        override fun getCount(): Int {
            return names.size
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getItem(position: Int): Any {
            return "TEST STRING"
        }

        //For rendering out each row
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val layoutInflater = LayoutInflater.from(parent?.context)
            val rowMain = layoutInflater.inflate(R.layout.row_main, parent, false)

            val airLineNameTextView = rowMain.findViewById<TextView>(R.id.airLineName)
            airLineNameTextView.text = names[position]

            val arrivalTimeTextView = rowMain.findViewById<TextView>(R.id.arrivalTime)
            arrivalTimeTextView.text = time[position]

            val terminalNumberTextView = rowMain.findViewById<TextView>(R.id.terminalNumber)
            terminalNumberTextView.text = "T${position + 1}"

            return rowMain
        }
    }

    companion object{
        private val names = arrayListOf<String>(
            "Air Canada", "Air France", "Air UK", "Air US", "Air Rose Mit"
        )

        private val time = arrayListOf<String>(
            "11:22 AM", "12:34 PM", "09:45 AM", "03:00 PM", "05:55 PM"
        )
    }

}