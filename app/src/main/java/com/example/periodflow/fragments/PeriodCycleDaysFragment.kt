package com.example.periodflow.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.periodflow.R
import com.example.periodflow.adapters.NumbersAdapter
import com.example.periodflow.databinding.FragmentPeriodCycleDaysBinding
import com.example.periodflow.utils.Utils
import com.google.common.base.MoreObjects.ToStringHelper

class PeriodCycleDaysFragment : Fragment() {
    private val binding by lazy {
        FragmentPeriodCycleDaysBinding.inflate(layoutInflater)
    }
    var selectedNumber = 10
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.picker1.maxValue = 40
        binding.picker1.minValue = 10

        binding.picker1.setOnValueChangedListener { _, _, newVal ->
            selectedNumber = newVal
        }

        binding.btnDoneLast.setOnClickListener {
            Utils.periodduration = selectedNumber.toString()
            findNavController().navigate(R.id.action_periodCycleDaysFragment_to_birthdayDateFragment)
        }
        binding.constraintLayout3.setOnClickListener {
            fragmentManager?.popBackStackImmediate()
        }
        return binding.root
    }
}