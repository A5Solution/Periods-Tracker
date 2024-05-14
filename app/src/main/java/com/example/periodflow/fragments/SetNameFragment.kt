package com.example.periodflow.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.periodflow.R
import com.example.periodflow.databinding.FragmentSetNameBinding
import com.example.periodflow.utils.Utils

class SetNameFragment : Fragment() {
    private val binding by lazy {
        FragmentSetNameBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.btnDoneEnd.setOnClickListener {
            if(binding.editTextText.text.isEmpty())
            {
                Toast.makeText(requireContext(), "Kindly enter name", Toast.LENGTH_SHORT).show()
            }
            else{
                Utils.username = binding.editTextText.text.toString()
                Handler(Looper.getMainLooper()).postDelayed({
                    Utils.setBasicInfo()
                    findNavController().navigate(R.id.action_setNameFragment_to_homeFragment)
                }, 1000)
            }
        }

        binding.constraintLayout3.setOnClickListener {
            fragmentManager?.popBackStackImmediate()
        }
        return binding.root
    }
}