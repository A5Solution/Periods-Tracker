package com.example.periodflow.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.periodflow.databinding.FragmentAddWeightBinding

class AddWeightFragment : Fragment() {
    private val binding by lazy {
        FragmentAddWeightBinding.inflate(layoutInflater)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding.picker1.maxValue=140
        binding.picker1.minValue=20
        binding.constraintLayout3.setOnClickListener {
            fragmentManager?.popBackStackImmediate()
        }
        return binding.root
    }
}