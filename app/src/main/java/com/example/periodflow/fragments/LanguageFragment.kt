package com.example.periodflow.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.periodflow.R
import com.example.periodflow.activities.HomeActivity
import com.example.periodflow.utils.SharePref
import com.example.periodflow.databinding.FragmentLanguageBinding


class LanguageFragment : Fragment() {
    private val binding by lazy {
        FragmentLanguageBinding.inflate(layoutInflater)
    }
    var langugage: String? = "en"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        SharePref.logAnalytic("Language_screen")

        setView()
        setSelectedLanguage()
        binding.parentFrench.setOnClickListener {
            setView()
            langugage = "fr"
            binding.radioFrench.visibility = View.VISIBLE
            binding.parentFrench.setBackgroundResource(R.drawable.bg_language)
            binding.parentSelectedLang.setBackgroundResource(R.drawable.bg_language_not_selected)
        }
        binding.parentJapanese.setOnClickListener {
            setView()
            langugage = "ja"
            binding.radioJapenese.visibility = View.VISIBLE
            binding.parentJapanese.setBackgroundResource(R.drawable.bg_language)
            binding.parentSelectedLang.setBackgroundResource(R.drawable.bg_language_not_selected)
        }
        binding.parentSpanish.setOnClickListener {
            setView()
            langugage = "es"
            binding.radioSpanish.visibility = View.VISIBLE
            binding.parentSpanish.setBackgroundResource(R.drawable.bg_language)
            binding.parentSelectedLang.setBackgroundResource(R.drawable.bg_language_not_selected)
        }
        binding.parentEnglish.setOnClickListener {
            setView()
            langugage = "en"
            binding.radioEnglish.visibility = View.VISIBLE
            binding.parentEnglish.setBackgroundResource(R.drawable.bg_language)
            binding.parentSelectedLang.setBackgroundResource(R.drawable.bg_language_not_selected)
        }
        binding.parentKorean.setOnClickListener {
            setView()
            langugage = "ko"
            binding.radioKorean.visibility = View.VISIBLE
            binding.parentKorean.setBackgroundResource(R.drawable.bg_language)
            binding.parentSelectedLang.setBackgroundResource(R.drawable.bg_language_not_selected)
        }
        binding.parentChinese.setOnClickListener {
            setView()
            langugage = "zh"
            binding.radioChinese.visibility = View.VISIBLE
            binding.parentChinese.setBackgroundResource(R.drawable.bg_language)
            binding.parentSelectedLang.setBackgroundResource(R.drawable.bg_language_not_selected)
        }
        binding.parentHindi.setOnClickListener {
            setView()
            langugage = "hi"
            binding.radioHindi.visibility = View.VISIBLE
            binding.parentHindi.setBackgroundResource(R.drawable.bg_language)
            binding.parentSelectedLang.setBackgroundResource(R.drawable.bg_language_not_selected)
        }
        binding.parentGerman.setOnClickListener {
            setView()
            langugage = "de"
            binding.radioGerman.visibility = View.VISIBLE
            binding.parentGerman.setBackgroundResource(R.drawable.bg_language)
            binding.parentSelectedLang.setBackgroundResource(R.drawable.bg_language_not_selected)
        }
        binding.parentArabic.setOnClickListener {
            setView()
            langugage = "ar"
            binding.radioArabic.visibility = View.VISIBLE
            binding.parentArabic.setBackgroundResource(R.drawable.bg_language)
            binding.parentSelectedLang.setBackgroundResource(R.drawable.bg_language_not_selected)
        }
        binding.parentItalain.setOnClickListener {
            setView()
            langugage = "it"
            binding.radioItalain.visibility = View.VISIBLE
            binding.parentItalain.setBackgroundResource(R.drawable.bg_language)
            binding.parentSelectedLang.setBackgroundResource(R.drawable.bg_language_not_selected)
        }
        binding.parentRusia.setOnClickListener {
            setView()
            langugage = "ru"
            binding.radioRussia.visibility = View.VISIBLE
            binding.parentRusia.setBackgroundResource(R.drawable.bg_language)
            binding.parentSelectedLang.setBackgroundResource(R.drawable.bg_language_not_selected)
        }
        binding.parentVietname.setOnClickListener {
            setView()
            langugage = "vi"
            binding.radioVietname.visibility = View.VISIBLE
            binding.parentVietname.setBackgroundResource(R.drawable.bg_language)
            binding.parentSelectedLang.setBackgroundResource(R.drawable.bg_language_not_selected)
        }
        binding.parentTurkey.setOnClickListener {
            setView()
            langugage = "tr"
            binding.radioTurkey.visibility = View.VISIBLE
            binding.parentTurkey.setBackgroundResource(R.drawable.bg_language)
            binding.parentSelectedLang.setBackgroundResource(R.drawable.bg_language_not_selected)
        }

        binding.btnDone.setOnClickListener {
            SharePref.putString("language", langugage!!)
            startActivity(Intent(requireActivity(), HomeActivity::class.java))
            requireActivity().finish()
        }
        return binding.root
    }

    private fun setSelectedLanguage() {
        val selectedLanguage: String = SharePref.getString("language", "en")
        if(selectedLanguage == "en")
        {
            binding.radioSelectedLanguage.visibility = View.VISIBLE
            binding.imageView29.setImageResource(R.drawable.flag_usa)
            binding.txtSelectedLang.text = "English"
//            binding.parentEnglish.visibility = View.GONE
        }
        else if(selectedLanguage == "es")
        {
            binding.radioSpanish.visibility = View.VISIBLE
            binding.imageView29.setImageResource(R.drawable.flag_spain)
            binding.txtSelectedLang.text = "Spanish"
//            binding.parentSpanish.visibility = View.GONE
        }
        else if(selectedLanguage == "fr")
        {
            binding.radioFrench.visibility = View.VISIBLE
            binding.imageView29.setImageResource(R.drawable.flag_france)
            binding.txtSelectedLang.text = "French"
//            binding.parentFrench.visibility = View.GONE
        }
        else if(selectedLanguage == "ja")
        {
            binding.radioJapenese.visibility = View.VISIBLE
            binding.imageView29.setImageResource(R.drawable.flag_japan)
            binding.txtSelectedLang.text = "Japanese"
//            binding.parentJapanese.visibility = View.GONE
        }
        else if(selectedLanguage == "ko")
        {
            binding.radioKorean.visibility = View.VISIBLE
            binding.imageView29.setImageResource(R.drawable.flag_korea)
            binding.txtSelectedLang.text = "Korean"
//            binding.parentKorean.visibility = View.GONE
        }
        else if(selectedLanguage == "zh")
        {
            binding.radioChinese.visibility = View.VISIBLE
            binding.imageView29.setImageResource(R.drawable.flag_china)
            binding.txtSelectedLang.text = "Chinese"
//            binding.parentChinese.visibility = View.GONE
        }
        else if(selectedLanguage == "hi")
        {
            binding.radioHindi.visibility = View.VISIBLE
            binding.imageView29.setImageResource(R.drawable.flag_india)
            binding.txtSelectedLang.text = "Hindi"
//            binding.parentHindi.visibility = View.GONE
        }
        else if(selectedLanguage == "de")
        {
            binding.radioGerman.visibility = View.VISIBLE
            binding.imageView29.setImageResource(R.drawable.flag_germany)
            binding.txtSelectedLang.text = "German"
//            binding.parentGerman.visibility = View.GONE
        }
        else if(selectedLanguage == "ar")
        {
            binding.radioArabic.visibility = View.VISIBLE
            binding.imageView29.setImageResource(R.drawable.flag_saudi)
            binding.txtSelectedLang.text = "Arabic"
//            binding.parentArabic.visibility = View.GONE
        }
        else if(selectedLanguage == "it")
        {
            binding.radioItalain.visibility = View.VISIBLE
            binding.imageView29.setImageResource(R.drawable.flag_italy)
            binding.txtSelectedLang.text = "Italian"
//            binding.parentItalain.visibility = View.GONE
        }
        else if(selectedLanguage == "ru")
        {
            binding.radioItalain.visibility = View.VISIBLE
            binding.imageView29.setImageResource(R.drawable.flag_russia)
            binding.txtSelectedLang.text = "Russia"
//            binding.parentRusia.visibility = View.GONE
        }
        else if(selectedLanguage == "vi")
        {
            binding.radioVietname.visibility = View.VISIBLE
            binding.imageView29.setImageResource(R.drawable.flag_vietname)
            binding.txtSelectedLang.text = "Vietnamese"
//            binding.parentVietname.visibility = View.GONE
        }
        else if(selectedLanguage == "tr")
        {
            binding.radioTurkey.visibility = View.VISIBLE
            binding.imageView29.setImageResource(R.drawable.flag_turkey)
            binding.txtSelectedLang.text = "Turkish"
//            binding.parentTurkey.visibility = View.GONE
        }
    }


    fun setView()
    {
        binding.parentChinese.setBackgroundResource(R.drawable.bg_language_not_selected)
        binding.parentEnglish.setBackgroundResource(R.drawable.bg_language_not_selected)
        binding.parentFrench.setBackgroundResource(R.drawable.bg_language_not_selected)
        binding.parentKorean.setBackgroundResource(R.drawable.bg_language_not_selected)
        binding.parentJapanese.setBackgroundResource(R.drawable.bg_language_not_selected)
        binding.parentSpanish.setBackgroundResource(R.drawable.bg_language_not_selected)
        binding.parentGerman.setBackgroundResource(R.drawable.bg_language_not_selected)
        binding.parentHindi.setBackgroundResource(R.drawable.bg_language_not_selected)
        binding.parentArabic.setBackgroundResource(R.drawable.bg_language_not_selected)
        binding.parentItalain.setBackgroundResource(R.drawable.bg_language_not_selected)
        binding.parentRusia.setBackgroundResource(R.drawable.bg_language_not_selected)
        binding.parentVietname.setBackgroundResource(R.drawable.bg_language_not_selected)
        binding.parentTurkey.setBackgroundResource(R.drawable.bg_language_not_selected)

        binding.radioChinese.visibility = View.GONE
        binding.radioEnglish.visibility = View.GONE
        binding.radioFrench.visibility = View.GONE
        binding.radioKorean.visibility = View.GONE
        binding.radioJapenese.visibility = View.GONE
        binding.radioSpanish.visibility = View.GONE
        binding.radioHindi.visibility = View.GONE
        binding.radioGerman.visibility = View.GONE
        binding.radioArabic.visibility = View.GONE
        binding.radioItalain.visibility = View.GONE
        binding.radioRussia.visibility = View.GONE
        binding.radioVietname.visibility = View.GONE
        binding.radioTurkey.visibility = View.GONE
    }
}