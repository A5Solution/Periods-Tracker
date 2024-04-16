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
    private lateinit var adapter: NumbersAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        adapter = NumbersAdapter((1..50).toList())
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext(),
            RecyclerView.VERTICAL, false)
        binding.recyclerView.adapter = adapter

        binding.btnDoneLast.setOnClickListener {
            findNavController().navigate(R.id.action_periodCycleFragment_to_periodCycleDaysFragment)
        }
        return binding.root
    }
}