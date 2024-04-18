package com.example.periodflow.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout.HORIZONTAL
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.periodflow.R
import com.example.periodflow.adapters.NumbersAdapter
import com.example.periodflow.databinding.FragmentPeriodCycleBinding


class PeriodCycleFragment : Fragment() {
    private val binding by lazy {
        FragmentPeriodCycleBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding.picker1.maxValue=20
        binding.picker1.minValue=2

        binding.btnDoneLast.setOnClickListener {
            findNavController().navigate(R.id.action_periodCycleFragment_to_periodCycleDaysFragment)
        }
        return binding.root
    }
}