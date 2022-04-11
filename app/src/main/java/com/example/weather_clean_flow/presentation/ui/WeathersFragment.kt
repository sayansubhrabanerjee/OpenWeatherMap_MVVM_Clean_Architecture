package com.example.weather_clean_flow.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.weather_clean_flow.common.util.UiStates
import com.example.weather_clean_flow.databinding.FragmentWeathersBinding
import com.example.weather_clean_flow.domain.model.Weather
import com.example.weather_clean_flow.presentation.adapter.WeatherAdapter
import com.example.weather_clean_flow.presentation.viewmodel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class WeathersFragment : Fragment() {

    private var _binding: FragmentWeathersBinding? = null
    private val binding get() = _binding!!

    private val weatherViewModel: WeatherViewModel by activityViewModels()
    private var weatherAdapter: WeatherAdapter? = null
    private var weathers: List<Weather> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWeathersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        updateUi()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        weatherAdapter = null
        _binding = null
    }

    private var onWeatherClicked: (Weather) -> Unit = {
        weatherViewModel.setWeather(it)
        val action = WeathersFragmentDirections.actionWeathersFragmentToWeatherDetailsFragment()
        findNavController().navigate(action)
    }

    private fun setAdapter() {
        weatherAdapter = WeatherAdapter(onItemClick = onWeatherClicked)
        binding.recyclerViewWeathers.apply {
            adapter = weatherAdapter
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            itemAnimator = DefaultItemAnimator()
        }
        weatherAdapter?.apply {
            stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
            update(weathers)
        }
    }

    private fun updateUi() {
        lifecycleScope.launchWhenStarted {
            weatherViewModel.uiStates.collectLatest {
                when (it) {
                    UiStates.Empty -> {
                        binding.progressBar.isVisible = false
                        binding.offlineView.isVisible = false
                        binding.recyclerViewWeathers.isVisible = false
                    }
                    UiStates.Loading -> {
                        binding.progressBar.isVisible = true
                        binding.offlineView.isVisible = false
                        binding.recyclerViewWeathers.isVisible = false
                    }
                    is UiStates.Success -> {
                        binding.progressBar.isVisible = false
                        binding.offlineView.isVisible = false
                        binding.recyclerViewWeathers.isVisible = true
                        weatherAdapter?.update(it.weathers)
                    }
                    is UiStates.Error -> {
                        binding.progressBar.isVisible = false
                        binding.offlineView.isVisible = true
                        binding.recyclerViewWeathers.isVisible = false
                        Log.i("mytest: ", "Error: ${it.throwable}")
                    }
                }
            }
        }
    }
}