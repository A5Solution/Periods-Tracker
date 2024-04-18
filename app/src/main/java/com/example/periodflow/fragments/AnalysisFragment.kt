package com.example.periodflow.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.periodflow.R
import com.example.periodflow.databinding.FragmentAnalysisBinding


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

        return binding.root
    }

}