package com.example.periodflow.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.periodflow.R
import com.example.periodflow.databinding.FragmentLanguageFirstBinding

class LanguageFragmentFirst : Fragment() {
    private val binding by lazy {
        FragmentLanguageFirstBinding.inflate(layoutInflater)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.btnSelectLanguage.setOnClickListener {
            findNavController().navigate(R.id.action_languageFragmentFirst_to_languageFragment)
        }
        return binding.root
    }
}