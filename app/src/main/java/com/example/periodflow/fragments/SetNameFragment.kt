package com.example.periodflow.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.periodflow.R
import com.example.periodflow.databinding.FragmentSetNameBinding

class SetNameFragment : Fragment() {
    private val binding by lazy {
        FragmentSetNameBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.btnDoneEnd.setOnClickListener {
            findNavController().navigate(R.id.action_setNameFragment_to_homeFragment)
        }
        return binding.root
    }
}