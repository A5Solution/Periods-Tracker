package com.example.periodflow.fragments

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.periodflow.R
import com.example.periodflow.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private val binding by lazy {
        FragmentLoginBinding.inflate(layoutInflater)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding.editTextTextPassword.setTextColor(Color.WHITE)
        binding.editTextTextPassword.background.mutate().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN)
        binding.edtEmail.setTextColor(Color.WHITE)
        binding.edtEmail.background.mutate().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN)

        binding.btnSignUp.setOnClickListener {

        }
        return binding.root
    }
}