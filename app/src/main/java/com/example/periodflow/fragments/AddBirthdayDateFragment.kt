package com.example.periodflow.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.periodflow.databinding.FragmentAddBirthdayDateBinding

class AddBirthdayDateFragment : Fragment() {
    private val binding by lazy {
        FragmentAddBirthdayDateBinding.inflate(layoutInflater)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return binding.root
    }
}