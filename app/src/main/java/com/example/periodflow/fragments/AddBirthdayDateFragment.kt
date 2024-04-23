package com.example.periodflow.fragments

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.findNavController
import com.example.periodflow.R
import com.example.periodflow.databinding.FragmentAddBirthdayDateBinding
import com.example.periodflow.utils.Utils
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class AddBirthdayDateFragment : Fragment() {
    private val binding by lazy {
        FragmentAddBirthdayDateBinding.inflate(layoutInflater)
    }

    var selectedDate: String = ""

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val today = Calendar.getInstance()
        binding.calendarView2.init(today.get(Calendar.YEAR), today.get(Calendar.MONTH), today.get(
            Calendar.DAY_OF_MONTH), null)

        val selectedDayOfMonth = binding.calendarView2.dayOfMonth
        val selectedMonth = binding.calendarView2.month
        val selectedYear = binding.calendarView2.year

        selectedDate = formatDate(selectedDayOfMonth, selectedMonth, selectedYear)
        binding.calendarView2.setOnDateChangedListener { _, year, monthOfYear, dayOfMonth ->
            selectedDate = formatDate(dayOfMonth, monthOfYear, year)
        }
        binding.constraintLayout3.setOnClickListener {
            fragmentManager?.popBackStackImmediate()
        }

        binding.btnDoneLast.setOnClickListener {
            Utils.birthdaydate = selectedDate
            Utils.setValueToFirebase("birthdaydate", selectedDate){

            }
            fragmentManager?.popBackStackImmediate()
        }
        return binding.root
    }

    private fun formatDate(day: Int, month: Int, year: Int): String {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, day)
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return dateFormat.format(calendar.time)
    }
}