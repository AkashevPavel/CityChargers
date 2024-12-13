package com.akashev.citychargers.presentation.cities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.akashev.citychargers.R
import com.akashev.citychargers.databinding.FragmentCitiesBinding
import com.akashev.citychargers.presentation.repeatOnStarted

class CitiesFragment : Fragment() {

    private lateinit var binding: FragmentCitiesBinding

    private val viewModel by viewModels<CitiesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCitiesBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        repeatOnStarted {
            viewModel.uiState.collect {

            }
        }
        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_CitiesFragment_to_ChargersFragment)
        }
    }
}