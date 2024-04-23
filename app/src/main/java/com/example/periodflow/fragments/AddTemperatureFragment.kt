package com.example.periodflow.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.periodflow.databinding.FragmentAddTemperatureBinding
import com.example.periodflow.utils.Utils
import java.text.SimpleDateFormat
import java.util.Calendar


class AddTemperatureFragment : Fragment() {
    private val binding by lazy {
        FragmentAddTemperatureBinding.inflate(layoutInflater)
    }
    var selectedNumber = 23

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.picker1.maxValue=41
        binding.picker1.minValue=30


        binding.constraintLayout3.setOnClickListener {
            fragmentManager?.popBackStackImmediate()
        }

        binding.picker1.setOnValueChangedListener { _, _, newVal ->
            selectedNumber = newVal
        }

        binding.btnDoneLast.setOnClickListener {
            Log.d("Number", "onCreateView: $selectedNumber")
            Utils.temperature = selectedNumber.toString()
            Utils.setTwoValuesToFirebase("temperature", selectedNumber.toString(), "temperatureaddeddate", getCurrentDate()){
            }
            fragmentManager?.popBackStackImmediate()
        }
        return binding.root
    }
    fun getCurrentDate(): String {
        val formatter = SimpleDateFormat("dd/MM/yyyy")
        val currentDate = Calendar.getInstance().time
        return formatter.format(currentDate)
    }
}