package com.example.periodflow.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.periodflow.R
import com.example.periodflow.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
   private val binding by lazy {
       FragmentHomeBinding.inflate(layoutInflater)
   }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.progressBar.progress = 33 // This sets the progress to 33%
        binding.progressBar.secondaryProgress = 40 // This sets the secondary progress to 40%
        binding.progressBar.max = 100


        binding.progressBar.progressDrawable = ContextCompat.getDrawable(requireContext(), R.drawable.my_progressbar)
        binding.btnProfile.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_navAccountFragment)
        }

        binding.textView27.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_calendarFragmentFirst)
        }

        binding.imageView32.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_analysisFragment)
        }
        return binding.root
    }
}