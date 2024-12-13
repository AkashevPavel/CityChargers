package com.akashev.citychargers.presentation.cities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.akashev.citychargers.R
import com.akashev.citychargers.databinding.FragmentCitiesBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class CitiesFragment : Fragment() {

    private lateinit var binding: FragmentCitiesBinding

    private val viewModel: CitiesViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCitiesBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_CitiesFragment_to_ChargersFragment)
        }
    }
}