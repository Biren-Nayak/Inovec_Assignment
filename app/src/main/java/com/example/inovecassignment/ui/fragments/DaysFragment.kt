package com.example.inovecassignment.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.inovecassignment.R
import com.example.inovecassignment.adapters.DateListAdapter
import com.example.inovecassignment.constants.*
import com.example.inovecassignment.databinding.FragmentDaysBinding
import com.example.inovecassignment.viewmodel.DaysViewModel

class DaysFragment: Fragment() {

    private lateinit var binding: FragmentDaysBinding
    private lateinit var daysViewModel: DaysViewModel
    private lateinit var adapter: DateListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDaysBinding.inflate(layoutInflater)
        adapter = DateListAdapter()

        // For handling back press
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            findNavController().navigate(R.id.action_daysFragment_to_homeFragment)
            requireActivity().findViewById<TextView>(R.id.textView).text = getString(R.string.app_name)
        }
        val name = DaysFragmentArgs.fromBundle(requireArguments()).city
        requireActivity().findViewById<TextView>(R.id.textView).text = name

        daysViewModel = DaysViewModel(name)

        binding.apply {
            viewmodel = daysViewModel
            lifecycleOwner = viewLifecycleOwner
            recyclerView.adapter = adapter
        }



        daysViewModel.list.observe(viewLifecycleOwner,{ result ->
            adapter.differ.submitList(result)
        })

        daysViewModel.weatherMode.observe(viewLifecycleOwner, { state ->
            binding.image.setImageResource(
            when(state){
                "Rain" -> R.drawable.rainy
                "Clouds" -> R.drawable.cloudy
                "Snow" -> R.drawable.snowy
                "Extreme" -> R.drawable.stormy
                "Clear" -> R.drawable.sunny
                else -> R.drawable.icon_sky
            })
        })

        daysViewModel.loadingStates.observe(viewLifecycleOwner, { state ->
            when(state){
                LoadingStates.LOADING -> showProgressBar()
                LoadingStates.SUCCESS -> hideProgressBar()
                LoadingStates.ERROR -> hideProgressBar().also {
                    Toast.makeText(context, getString(R.string.error_text), Toast.LENGTH_SHORT).show()
                }
                else -> {}
            }
        })

        return binding.root
    }

    private fun showProgressBar(){
        binding.progress.visibility = View.VISIBLE
    }

    private fun hideProgressBar(){
        binding.progress.visibility = View.GONE
    }
}