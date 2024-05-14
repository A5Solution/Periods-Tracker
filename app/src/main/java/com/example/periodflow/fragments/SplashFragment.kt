package com.example.periodflow.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.periodflow.R
import com.example.periodflow.databinding.FragmentSplashBinding
import com.example.periodflow.viewmodel.SharedViewModel

class SplashFragment : Fragment() {
    private val binding by lazy {
        FragmentSplashBinding.inflate(layoutInflater)
    }
    private lateinit var viewModel: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]
        viewModel.firebaseAuthIntialize()

        viewModel.alreadyLogedIn {
            if(it)
            {
                findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
            }
        }

        binding.btnLogin.setOnClickListener {
            findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
        }
        binding.btnSignup.setOnClickListener {
            findNavController().navigate(R.id.action_splashFragment_to_signupFragment)
        }
        return binding.root
    }
}