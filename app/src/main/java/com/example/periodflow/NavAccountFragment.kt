package com.example.periodflow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.periodflow.databinding.FragmentNavAccountBinding

class NavAccountFragment : Fragment() {
    private val binding by lazy {
        FragmentNavAccountBinding.inflate(layoutInflater)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return binding.root
    }
}