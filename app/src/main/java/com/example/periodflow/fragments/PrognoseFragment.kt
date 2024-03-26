package com.example.periodflow.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.periodflow.R
import com.example.periodflow.databinding.FragmentPrognoseBinding

class PrognoseFragment : Fragment() {
    private val binding by lazy {
        FragmentPrognoseBinding.inflate(layoutInflater)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.btnDoneLast.setOnClickListener {
            findNavController().navigate(R.id.action_prognoseFragment_to_setNameFragment)
        }
        return binding.root
    }
}