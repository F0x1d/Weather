package com.f0x1d.weather.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.f0x1d.weather.adapter.CitiesAdapter
import com.f0x1d.weather.databinding.FragmentCitiesBinding
import com.f0x1d.weather.ui.fragment.base.BaseFragment
import com.f0x1d.weather.viewmodel.CitiesViewModel
import com.f0x1d.weather.viewmodel.base.DataState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CitiesFragment: BaseFragment<FragmentCitiesBinding>() {
    val viewModel by viewModels<CitiesViewModel>()

    private val adapter = CitiesAdapter()

    override fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?) = FragmentCitiesBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchCityText.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                viewModel.search(binding.searchCityText.text?.toString()?.trim())
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }

        binding.citiesRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.citiesRecycler.adapter = adapter

        viewModel.stateData.observe(viewLifecycleOwner) {
            when (it) {
                is DataState.Loading -> {
                    binding.loadingProgress.visibility = View.VISIBLE
                    binding.searchCityLayout.isEnabled = false
                    binding.citiesRecycler.visibility = View.INVISIBLE
                }
                is DataState.Loaded -> {
                    binding.loadingProgress.visibility = View.GONE
                    binding.searchCityLayout.isEnabled = true
                    binding.citiesRecycler.visibility = View.VISIBLE

                    adapter.elements = it.data
                }
                is DataState.Error -> {
                    binding.loadingProgress.visibility = View.GONE
                    binding.searchCityLayout.isEnabled = true
                    binding.citiesRecycler.visibility = View.VISIBLE

                    adapter.elements = emptyList()
                }
            }
        }
    }
}