package com.f0x1d.weather.ui.fragment

import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.f0x1d.weather.R
import com.f0x1d.weather.databinding.FragmentWeatherBinding
import com.f0x1d.weather.extensions.setupViews
import com.f0x1d.weather.network.model.WeatherResponse
import com.f0x1d.weather.ui.fragment.base.BaseFragment
import com.f0x1d.weather.viewmodel.WeatherViewModel
import com.f0x1d.weather.viewmodel.base.DataState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherFragment : BaseFragment<FragmentWeatherBinding>() {
    val viewModel by viewModels<WeatherViewModel>()

    override fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?) = FragmentWeatherBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.swipeRefresh.setOnRefreshListener {
            viewModel.load()
        }

        binding.cityNameText.setOnClickListener {
            viewModel.clearSelectedLocation()
            openSetup()
        }
        binding.cityNameText.paintFlags = binding.cityNameText.paintFlags or Paint.UNDERLINE_TEXT_FLAG

        viewModel.stateData.observe(viewLifecycleOwner) {
            when (it) {
                is DataState.Loading -> {
                    binding.swipeRefresh.isRefreshing = true
                }
                is DataState.Loaded -> {
                    binding.swipeRefresh.isRefreshing = false

                    setupFor(it.data)
                }
                is DataState.Error -> {
                    binding.swipeRefresh.isRefreshing = false

                    binding.cityNameText.setText(R.string.error)
                    binding.countryNameText.text = it.t.localizedMessage
                }
            }
        }

        viewModel.eventsData.observe(viewLifecycleOwner) {
            if (it.isConsumed) return@observe

            when (it.type) {
                WeatherViewModel.EVENT_TYPE_SETUP -> openSetup().apply {
                    it.consume<Unit>()
                }
            }
        }
    }

    private fun setupFor(weather: WeatherResponse) = weather.setupViews(
        binding.conditionImage,
        binding.cityWeatherText,
        binding.cityNameText,
        binding.countryNameText
    )

    private fun openSetup() {
        findNavController().navigate(R.id.action_WeatherFragment_to_SetupFragment)
    }
}