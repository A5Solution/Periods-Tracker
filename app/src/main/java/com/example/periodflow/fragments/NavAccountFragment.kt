package com.example.periodflow.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.periodflow.R
import com.example.periodflow.databinding.FragmentNavAccountBinding
import com.example.periodflow.viewmodel.SharedViewModel

class NavAccountFragment : Fragment() {
    private val binding by lazy {
        FragmentNavAccountBinding.inflate(layoutInflater)
    }
    private lateinit var viewModel: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]

        binding.constraintLayout3.setOnClickListener {
            fragmentManager?.popBackStackImmediate()
        }

        binding.btnLogout.setOnClickListener {
            viewModel.logoutUser {
                if(it)
                {
                    Toast.makeText(requireContext(), "Logout successfully", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_navAccountFragment_to_loginFragment)
                }
            }
        }
        return binding.root
    }
}