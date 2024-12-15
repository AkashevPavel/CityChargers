package com.akashev.citychargers.presentation.cities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.akashev.citychargers.R
import com.akashev.citychargers.databinding.FragmentCitiesBinding
import com.akashev.citychargers.presentation.chargers.ChargersFragment.Companion.CITY_NAME
import com.akashev.citychargers.presentation.onTextChanged
import com.akashev.citychargers.presentation.repeatOnStarted
import com.akashev.citychargers.presentation.replaceText
import com.akashev.citychargers.presentation.showErrorMessage
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class CitiesFragment : Fragment() {

    private lateinit var binding: FragmentCitiesBinding

    private val viewModel: CitiesViewModel by viewModel()
    private val citiesAdapter by lazy { ArrayAdapter<String>(requireContext(),
        android.R.layout.simple_list_item_1) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCitiesBinding.inflate(inflater, container, false)
        binding.cityName.setAdapter(citiesAdapter)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        repeatOnStarted {
            viewModel.cityNames.collect {
                citiesAdapter.apply {
                    clear()
                    addAll(it)
                }
            }
        }
        repeatOnStarted {
            viewModel.selectedCity.collect {
                binding.cityName.replaceText(it)
                binding.confirmButton.isEnabled = it.isNotBlank()
                binding.confirmButton.setOnClickListener { _ ->
                    findNavController().navigate(
                        R.id.action_CitiesFragment_to_ChargersFragment,
                        bundleOf(CITY_NAME to it)
                    )
                }
            }
        }
        repeatOnStarted {
            binding.cityName.onTextChanged().collect(viewModel::setSelectedCity)
        }
        repeatOnStarted {
            viewModel.isLoading.collect {
                binding.loading.isVisible = it
            }
        }
        repeatOnStarted {
            viewModel.error.collect(this@CitiesFragment::showErrorMessage)
        }
    }
}