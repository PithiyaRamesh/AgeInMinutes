package com.ageinminutes

import android.app.DatePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    var tvAgeInMinutes: TextView? = null
    var tvSelectedDate: TextView? = null

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnSelectDate: Button = findViewById(R.id.btn_date_picker)
        tvAgeInMinutes = findViewById(R.id.tv_age_in_minutes)
        tvSelectedDate = findViewById(R.id.tv_selected_date)

        btnSelectDate.setOnClickListener{
            clickDatePicker()
        }

    }

    private fun clickDatePicker() {
        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val dayOfMonth = myCalender.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this, { mview, selectedYear, selectedMonth, selectedDayOfMonth ->
            val selectedDateStr = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
            tvSelectedDate!!.text = selectedDateStr
            val sdf = SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)
            val selectedDate = sdf.parse(selectedDateStr)
            val selectedDateInMinutes = selectedDate!!.time / 60000
            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
            val currentDateInMinutes = currentDate!!.time / 60000
            val differeMinutes = currentDateInMinutes - selectedDateInMinutes
            tvAgeInMinutes?.text = differeMinutes.toString()
        },year, month, dayOfMonth)
        dpd.datePicker.setMaxDate(Date().time - 86400000)
        dpd.show()
    }
}