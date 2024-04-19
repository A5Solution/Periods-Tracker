package com.example.periodflow.fragments

import android.os.Bundle
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
                Utils.setValueToFirebase("username", binding.editTextText.text.toString()){
                    if(it)
                    {
                        findNavController().navigate(R.id.action_setNameFragment_to_homeFragment)
                    }
                    else
                        Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_SHORT).show()
                }
            }
        }
        return binding.root
    }
}