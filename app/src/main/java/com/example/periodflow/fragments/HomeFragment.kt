package com.example.periodflow.fragments

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.LayerDrawable
import android.graphics.drawable.ShapeDrawable
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.periodflow.R
import com.example.periodflow.databinding.FragmentHomeBinding
import com.example.periodflow.utils.Utils
import com.example.periodflow.utils.Utils.Companion.calculateDaysSinceLastPeriod
import com.example.periodflow.utils.Utils.Companion.calculateFertileWindowStart
import com.example.periodflow.utils.Utils.Companion.calculateNextDate
import com.example.periodflow.utils.Utils.Companion.calculateNextPeriod
import com.example.periodflow.utils.Utils.Companion.calculateOvulationDate
import com.example.periodflow.utils.Utils.Companion.calculatePeriodDates
import com.example.periodflow.utils.Utils.Companion.calculateRemainingDays
import com.example.periodflow.utils.Utils.Companion.formatDate
import com.example.periodflow.utils.Utils.Companion.periodcycle
import com.example.periodflow.utils.Utils.Companion.periodduration

class HomeFragment : Fragment() {
   private val binding by lazy {
       FragmentHomeBinding.inflate(layoutInflater)
   }
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Utils.fetchBasicInfo(requireContext()){
            if(it)
            {
                UpdateUI()
            }else{
                Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_SHORT).show()
            }
        }

//        binding.progressBar.progressDrawable = ContextCompat.getDrawable(requireContext(), R.drawable.my_progressbar)


        binding.btnProfile.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_navAccountFragment)
        }
        binding.textView27.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_calendarFragmentFirst)
        }
        binding.imageView32.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_analysisFragment)
        }
        binding.imageView5.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_calendarFragmentFirst)
        }

        return binding.root
    }
    @RequiresApi(Build.VERSION_CODES.Q)
    private fun createCustomProgressDrawable(): GradientDrawable {
        val colors = intArrayOf(
            Color.RED, // Colors for progress 1-5
            Color.GRAY, // Colors for progress 6-8
            Color.YELLOW, // Colors for progress 9-15
            Color.GRAY, // Colors for progress 16-29
        )
        val colorPositions = floatArrayOf(
            0.0f,
            0.42f,
            0.43f,
            1.0f
        )
        return GradientDrawable(
            GradientDrawable.Orientation.LEFT_RIGHT, colors
        ).apply {
            setStroke(0, Color.TRANSPARENT) // Set stroke color and width
            setGradientCenter(0f, 0f) // Set gradient center
            setGradientType(GradientDrawable.LINEAR_GRADIENT) // Set gradient type
            setColors(colors, colorPositions)
        }
    }


    @RequiresApi(Build.VERSION_CODES.Q)
    private fun UpdateUI() {
        binding.textView26.text = calculateRemainingDays().toString()
        binding.txtNextPeriodMonth.text = calculateNextPeriod()
        binding.txtNextPeriodMonth1.text = formatDate(calculateNextPeriod()!!)
        binding.txtNextFertileMonth.text = formatDate(calculateFertileWindowStart()!!)
        binding.txtNextOvulationMonth.text = formatDate(calculateOvulationDate()!!)

        binding.progressBar.max = periodduration.toInt()
        binding.progressBar.progressDrawable = createCustomProgressDrawable()
        binding.progressBar.max = periodduration.toInt()
        binding.progressBar.setBackgroundColor(Color.GRAY)
        binding.progressBar.progress = calculateDaysSinceLastPeriod()
        binding.textView63.text = formatDate()
        binding.textView39.text = calculateDaysSinceLastPeriod().toString()

        //Period Progress
        binding.progressBarPeriod.min = 1
        binding.progressBarPeriod.max = periodduration.toInt()
        binding.progressBarPeriod.progress = periodcycle.toInt()
//        binding.textView33.text = formatDate(calculateNextDate())
        val (startDate, endDate) = calculatePeriodDates()
        binding.textView67.text = endDate
        binding.textView70.text = startDate

//        binding.progressBarPeriod
        binding.progressBar.setOnSeekBarChangeListener(object :SeekBar.OnSeekBarChangeListener{
            @SuppressLint("SetTextI18n")
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding.textView39.text = progress.toString()
                binding.textView33.text = formatDate(calculateNextDate(progress))
                if(progress <= 11)
                    binding.textView60.text = "Low - Chance of getting pregnant"
                else if(progress <= 18)
                    binding.textView60.text = "High - Chance of getting pregnant"
                else if(progress <= 20)
                    binding.textView60.text = "Medium - Chance of getting pregnant"
                else
                    binding.textView60.text = "Low - Chance of getting pregnant"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })
    }
}