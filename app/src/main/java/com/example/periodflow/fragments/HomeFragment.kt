package com.example.periodflow.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.periodflow.R
import com.example.periodflow.databinding.FragmentHomeBinding
import com.example.periodflow.utils.Utils
import com.example.periodflow.utils.Utils.Companion.calculateFertileWindowStart
import com.example.periodflow.utils.Utils.Companion.calculateNextPeriod
import com.example.periodflow.utils.Utils.Companion.calculateOvulationDate
import com.example.periodflow.utils.Utils.Companion.calculateRemainingDays

class HomeFragment : Fragment() {
   private val binding by lazy {
       FragmentHomeBinding.inflate(layoutInflater)
   }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Utils.fetchBasicInfo(requireContext()){
            if(it)
            {
                UpdateUI()
            }else{
                Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_SHORT).show()
            }
        }



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
        binding.imageView5.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_calendarFragmentFirst)
        }

        return binding.root
    }

    private fun UpdateUI() {
        binding.textView26.text = calculateRemainingDays().toString()
        binding.txtNextPeriodMonth.text = calculateNextPeriod()
        binding.txtNextPeriodMonth1.text = calculateNextPeriod()
        binding.txtNextFertileMonth.text = calculateFertileWindowStart()
        binding.txtNextOvulationMonth.text = calculateOvulationDate()
    }
}