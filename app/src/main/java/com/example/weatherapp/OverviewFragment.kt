package com.example.weatherapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.weatherapp.databinding.FragmentOverviewBinding
import com.example.weatherapp.view.CityGridAdapter

class OverviewFragment : Fragment() {

    private val viewModel: CityModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val fragmentBinding = FragmentOverviewBinding.inflate(inflater, container, false)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment

        fragmentBinding.lifecycleOwner = this
        fragmentBinding.viewModel = viewModel
        fragmentBinding.citiesGrid.adapter = CityGridAdapter()
        // Inflate the layout for this fragment
        return fragmentBinding.root
    }

}