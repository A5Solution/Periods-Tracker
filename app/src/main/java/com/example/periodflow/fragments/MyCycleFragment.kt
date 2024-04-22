package com.example.periodflow.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.periodflow.databinding.FragmentMyCycleBinding


class MyCycleFragment : Fragment() {
    private val binding by lazy {
        FragmentMyCycleBinding.inflate(layoutInflater)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.progressBar.progress = 33 // This sets the progress to 33%
        binding.progressBar.secondaryProgress = 40 // This sets the secondary progress to 40%
        binding.progressBar.max = 100

        binding.constraintLayout3.setOnClickListener {
            fragmentManager?.popBackStackImmediate()
        }

        return binding.root
    }
}