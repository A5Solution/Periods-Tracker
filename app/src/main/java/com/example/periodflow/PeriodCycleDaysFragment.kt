package com.example.periodflow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.periodflow.databinding.FragmentPeriodCycleDaysBinding

class PeriodCycleDaysFragment : Fragment() {
    private val binding by lazy {
        FragmentPeriodCycleDaysBinding.inflate(layoutInflater)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.btnDoneLast.setOnClickListener {
            findNavController().navigate(R.id.action_periodCycleDaysFragment_to_birthdayDateFragment)
        }
        return binding.root
    }
}