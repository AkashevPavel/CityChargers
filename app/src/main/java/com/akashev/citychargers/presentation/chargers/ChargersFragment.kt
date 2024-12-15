package com.akashev.citychargers.presentation.chargers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.akashev.citychargers.databinding.FragmentChargersBinding
import com.akashev.citychargers.presentation.repeatOnStarted
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChargersFragment : Fragment() {

    private lateinit var binding: FragmentChargersBinding
    private val chargersAdapter by lazy { ChargersAdapter() }
    private val viewmodel: ChargersViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewmodel.getChargers(requireArguments().getString(CITY_NAME)!!)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChargersBinding.inflate(inflater, container, false)
        binding.chargers.apply {
            addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))
            adapter = chargersAdapter
        }
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewmodel.getChargers(requireArguments().getString(CITY_NAME)!!)
        repeatOnStarted {
            viewmodel.chargers.collect(chargersAdapter::updateItems)
        }
        binding.backToCitiesButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    companion object {
        const val CITY_NAME = "city_name"
    }
}