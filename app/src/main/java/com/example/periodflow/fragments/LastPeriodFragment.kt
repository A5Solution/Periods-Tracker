package com.example.periodflow.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.periodflow.R
import com.example.periodflow.databinding.FragmentLanguageBinding
import com.example.periodflow.databinding.FragmentLastPeriodBinding

class LastPeriodFragment : Fragment() {
   private val binding by lazy {
       FragmentLastPeriodBinding.inflate(layoutInflater)
   }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.btnDoneLast.setOnClickListener {
            findNavController().navigate(R.id.action_lastPeriodFragment_to_birthdayDateFragment)
        }
        return binding.root
    }
}