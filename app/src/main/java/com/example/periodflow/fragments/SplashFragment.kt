package com.example.periodflow.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.periodflow.R
import com.example.periodflow.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {
    private val binding by lazy {
        FragmentSplashBinding.inflate(layoutInflater)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding.btnSignUp.setOnClickListener{
            findNavController().navigate(R.id.action_splashFragment_to_signupFragment)
        }
        binding.btnSignIn.setOnClickListener {
            findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
        }
        return binding.root
    }
}