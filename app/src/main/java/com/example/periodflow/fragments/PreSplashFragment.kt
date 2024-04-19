package com.example.periodflow.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.periodflow.R
import com.example.periodflow.databinding.FragmentPreSplashBinding
import com.example.periodflow.utils.SharePref

class PreSplashFragment : Fragment() {
    private val binding by lazy {
        FragmentPreSplashBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Handler(Looper.getMainLooper()).postDelayed({
            if(!SharePref.getBoolean("firstInstall"))
            {
                SharePref.putBoolean("firstInstall", true)
                findNavController().navigate(R.id.action_preSplashFragment_to_languageFragmentFirst)
            }
            else
            {
                findNavController().navigate(R.id.action_preSplashFragment_to_splashFragment)
            }
        }, 1500)
        return binding.root
    }
}