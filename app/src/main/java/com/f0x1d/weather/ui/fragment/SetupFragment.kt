package com.f0x1d.weather.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.f0x1d.weather.R
import com.f0x1d.weather.databinding.FragmentSetupBinding
import com.f0x1d.weather.ui.fragment.base.BaseFragment
import com.f0x1d.weather.utils.AppPreferences
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SetupFragment: BaseFragment<FragmentSetupBinding>() {
    @Inject
    lateinit var appPreferences: AppPreferences

    override fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?) = FragmentSetupBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.okButton.setOnClickListener {
            appPreferences.selectedCity = binding.cityText.text?.toString()?.trim()
            findNavController().navigate(R.id.action_SetupFragment_to_WeatherFragment)
        }
    }
}