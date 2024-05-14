package com.example.periodflow.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.periodflow.R
import com.example.periodflow.databinding.FragmentAnalysisBinding
import com.example.periodflow.utils.Utils


class AnalysisFragment : Fragment() {
    private val binding by lazy {
        FragmentAnalysisBinding.inflate(layoutInflater)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.constraintLayout9.setOnClickListener {
            findNavController().navigate(R.id.action_analysisFragment_to_myCycleFragment)
        }
        binding.textView92.setOnClickListener {
            findNavController().navigate(R.id.action_analysisFragment_to_addWeightFragment)
        }
        binding.textView94.setOnClickListener {
            findNavController().navigate(R.id.action_analysisFragment_to_addTemperatureFragment)
        }
        binding.textView89.setOnClickListener {
            findNavController().navigate(R.id.action_analysisFragment_to_addBirthdayDateFragment)
        }
        binding.constraintLayout3.setOnClickListener {
            fragmentManager?.popBackStackImmediate()
        }
        return binding.root
    }
    override fun onResume() {
        super.onResume()
        binding.textView88.text = Utils.weight + " Kg"
        binding.textView90.text = Utils.weightaddeddate
        binding.textView91.text = Utils.temperature + " C"
        binding.textView93.text = Utils.temperatureaddeddate
        binding.textView97.text = Utils.birthdaydate
    }
}