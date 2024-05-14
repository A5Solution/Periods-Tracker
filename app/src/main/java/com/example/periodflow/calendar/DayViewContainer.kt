package com.example.periodflow.calendar

import android.view.View
import com.example.periodflow.databinding.CalendarDayLayoutBinding
import com.kizitonwose.calendar.view.ViewContainer

class DayViewContainer(view: View) : ViewContainer(view) {
     val textView = CalendarDayLayoutBinding.bind(view).calendarDayText
}