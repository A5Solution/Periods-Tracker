package com.example.periodflow.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.periodflow.R
import com.example.periodflow.databinding.FragmentAddWeightBinding
import com.example.periodflow.utils.Utils
import java.text.SimpleDateFormat
import java.util.Calendar

class AddWeightFragment : Fragment() {
    private val binding by lazy {
        FragmentAddWeightBinding.inflate(layoutInflater)
    }
    var selectedNumber = 23

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.picker1.maxValue=140
        binding.picker1.minValue=20

        binding.picker1.setOnValueChangedListener { _, _, newVal ->
            selectedNumber = newVal
        }

        binding.constraintLayout3.setOnClickListener {
            fragmentManager?.popBackStackImmediate()
        }

        binding.btnDoneLast.setOnClickListener {
            Log.d("Number", "onCreateView: $selectedNumber")
            Utils.weight = selectedNumber.toString()
            Utils.setTwoValuesToFirebase("weight", selectedNumber.toString(), "weightaddeddate", getCurrentDate()){

            }
            fragmentManager?.popBackStackImmediate()
        }
    }

    fun getCurrentDate(): String {
        val formatter = SimpleDateFormat("dd/MM/yyyy")
        val currentDate = Calendar.getInstance().time
        return formatter.format(currentDate)
    }
}