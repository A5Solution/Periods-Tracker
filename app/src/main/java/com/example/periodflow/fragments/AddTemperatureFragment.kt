package com.example.periodflow.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.periodflow.databinding.FragmentAddTemperatureBinding


class AddTemperatureFragment : Fragment() {
    private val binding by lazy {
        FragmentAddTemperatureBinding.inflate(layoutInflater)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.picker1.maxValue=41
        binding.picker1.minValue=30
        binding.constraintLayout3.setOnClickListener {
            fragmentManager?.popBackStackImmediate()
        }
        return binding.root
    }
}