package com.example.periodflow.fragments

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.periodflow.R
import com.example.periodflow.databinding.FragmentLoginBinding
import com.example.periodflow.utils.Utils.Companion.fetchBasicInfo
import com.example.periodflow.viewmodel.SharedViewModel

class LoginFragment : Fragment() {
    private val binding by lazy {
        FragmentLoginBinding.inflate(layoutInflater)
    }
    private lateinit var viewModel: SharedViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]

        binding.editTextTextPassword.setTextColor(Color.WHITE)
        binding.editTextTextPassword.background.mutate().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN)
        binding.edtEmail.setTextColor(Color.WHITE)
        binding.edtEmail.background.mutate().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN)


        binding.btnSignIn.setOnClickListener {
            val email = binding.edtEmail.text.toString()
            val password = binding.editTextTextPassword.text.toString()

            if(email.isEmpty() || password.isEmpty())
            {
                Toast.makeText(requireContext(), "Please enter data", Toast.LENGTH_SHORT).show()
            }
            else{
                viewModel.loginUser(email, password){
                    if(it)
                    {
                        Toast.makeText(requireContext(), "Login successfully", Toast.LENGTH_SHORT).show()
                        fetchBasicInfo(requireContext()){
                            if(it)
                            {
                                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                            }
                            else{
                                findNavController().navigate(R.id.action_loginFragment_to_periodCycleFragment)
                            }
                        }
                    }
                    else
                    {
                        Toast.makeText(requireContext(), "Login failed", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        return binding.root
    }
}