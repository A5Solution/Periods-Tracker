package com.example.periodflow.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.periodflow.R
import com.example.periodflow.databinding.FragmentNavAccountBinding
import com.example.periodflow.utils.Utils
import com.example.periodflow.utils.Utils.Companion.contactUs
import com.example.periodflow.utils.Utils.Companion.openPlayStoreForRating
import com.example.periodflow.utils.Utils.Companion.openPrivacyPolicy
import com.example.periodflow.utils.Utils.Companion.shareApp
import com.example.periodflow.viewmodel.SharedViewModel

class NavAccountFragment : Fragment() {
    private val binding by lazy {
        FragmentNavAccountBinding.inflate(layoutInflater)
    }
    private lateinit var viewModel: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]

        binding.constraintLayout3.setOnClickListener {
            fragmentManager?.popBackStackImmediate()
        }

        binding.textView34.text = Utils.username
        binding.textView36.text = Utils.periodcycle
        binding.textView41.text = Utils.periodduration
        binding.textView43.text = Utils.calculateOvulationDate()

        binding.imageView14.setOnClickListener {
            findNavController().navigate(R.id.action_navAccountFragment_to_addWeightFragment)
        }
        binding.imageView34.setOnClickListener {
            findNavController().navigate(R.id.action_navAccountFragment_to_addTemperatureFragment)
        }
        binding.imageView35.setOnClickListener {
            findNavController().navigate(R.id.action_navAccountFragment_to_addBirthdayDateFragment)
        }
        binding.textView76.setOnClickListener {
            openPrivacyPolicy(
                requireContext(),
                "https://sites.google.com/view/privacypolicyofperiodtrackerap/home"
            )
        }
        binding.textView77.setOnClickListener {
            shareApp(requireContext())
        }
        binding.textView78.setOnClickListener {
            openPlayStoreForRating(requireContext())
        }
        binding.textView82.setOnClickListener {
            openPrivacyPolicy(
                requireContext(),
                "https://sites.google.com/view/periodtrackertermsservices/home"
            )
        }
        binding.textView79.setOnClickListener {
            openPrivacyPolicy(
                requireContext(),
                "https://play.google.com/store/apps/developer?id=Sparx+Developer"
            )
        }
        binding.textView80.setOnClickListener {
            contactUs(requireContext())
        }
        binding.btnLogout.setOnClickListener {
            viewModel.logoutUser {
                if(it)
                {
                    Toast.makeText(requireContext(), "Logout successfully", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_navAccountFragment_to_loginFragment)
                }
            }
        }
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        binding.textView48.text = Utils.weight + " Kg"
        binding.textView50.text = Utils.weightaddeddate
        binding.textView53.text = Utils.temperature + " C"
        binding.textView54.text = Utils.temperatureaddeddate
        binding.textView72.text = Utils.birthdaydate
    }
}