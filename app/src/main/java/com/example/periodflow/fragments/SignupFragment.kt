package com.example.periodflow.fragments

import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.periodflow.R
import com.example.periodflow.databinding.FragmentSignupBinding
import com.example.periodflow.viewmodel.SharedViewModel
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider


class SignupFragment : Fragment() {
    private val binding by lazy {
        FragmentSignupBinding.inflate(layoutInflater)
    }
    private lateinit var viewModel: SharedViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]
        viewModel.firebaseAuthIntialize()

        binding.edtEmail.setTextColor(Color.WHITE)
        binding.edtEmail.background.mutate().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN)
        binding.editTextTextPassword.setTextColor(Color.WHITE)
        binding.editTextTextPassword.background.mutate().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN)
        binding.edtPasswordConfirm.setTextColor(Color.WHITE)
        binding.edtPasswordConfirm.background.mutate().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN)


        binding.btnContinueWithGoogle.setOnClickListener {
            findNavController().navigate(R.id.action_signupFragment_to_periodCycleFragment)
        }
        binding.btnCreateAccount.setOnClickListener {
            val email = binding.edtEmail.text.toString()
            val password = binding.editTextTextPassword.text.toString()
            val passwordConfirm = binding.edtPasswordConfirm.text.toString()
            if(email.isBlank() || password.isBlank() || passwordConfirm.isBlank())
            {
                Toast.makeText(requireContext(), "Please enter all fields", Toast.LENGTH_SHORT).show()
            }
            else if(!password.equals(passwordConfirm))
            {
                Toast.makeText(requireContext(), "Password does not match", Toast.LENGTH_SHORT).show()
            }
            else
            {
                viewModel.signUpUser(email, password){
                    if(it)
                    {
                        findNavController().navigate(R.id.action_signupFragment_to_periodCycleFragment)
                    }
                    else{
                        Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        return binding.root
    }
}