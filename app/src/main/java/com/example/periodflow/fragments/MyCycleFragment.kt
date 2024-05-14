package com.example.periodflow.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.periodflow.R
import com.example.periodflow.databinding.FragmentMyCycleBinding
import com.example.periodflow.utils.Utils


class MyCycleFragment : Fragment() {
    private val binding by lazy {
        FragmentMyCycleBinding.inflate(layoutInflater)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.progressBar.progress = 33
        binding.progressBar.secondaryProgress = 40
        binding.progressBar.max = 100

        binding.textView100.text = Utils.periodcycle
        binding.textView1100.text = Utils.periodduration

        binding.constraintLayout3.setOnClickListener {
            fragmentManager?.popBackStackImmediate()
        }
        binding.textView103.setOnClickListener {
            findNavController().navigate(R.id.action_myCycleFragment_to_calendarFragmentFirst)
        }

        return binding.root
    }
}