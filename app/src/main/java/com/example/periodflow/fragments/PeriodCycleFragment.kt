package com.example.periodflow.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout.HORIZONTAL
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.periodflow.R
import com.example.periodflow.adapters.NumbersAdapter
import com.example.periodflow.databinding.FragmentPeriodCycleBinding
import com.example.periodflow.utils.Utils


class PeriodCycleFragment : Fragment() {
    private val binding by lazy {
        FragmentPeriodCycleBinding.inflate(layoutInflater)
    }
    var selectedNumber = 2
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding.picker1.maxValue=20
        binding.picker1.minValue=2

        binding.picker1.setOnValueChangedListener { _, _, newVal ->
            selectedNumber = newVal
        }

        binding.btnDoneLast.setOnClickListener {
            Log.d("Number", "onCreateView: $selectedNumber")
            Utils.setValueToFirebase("periodcycle", selectedNumber.toString()){
                if(it)
                {
                    findNavController().navigate(R.id.action_periodCycleFragment_to_periodCycleDaysFragment)
                }
                else
                {
                    Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_SHORT).show()
                }
            }
        }
        return binding.root
    }
}