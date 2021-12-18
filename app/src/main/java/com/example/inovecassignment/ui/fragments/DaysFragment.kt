package com.example.inovecassignment.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.inovecassignment.R
import com.example.inovecassignment.adapters.DateListAdapter
import com.example.inovecassignment.constants.LoadingStates
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
        daysViewModel = DaysViewModel()
        adapter = DateListAdapter()

        binding.apply {
            viewmodel = daysViewModel
            lifecycleOwner = viewLifecycleOwner
            recyclerView.adapter = adapter
        }



        val name = DaysFragmentArgs.fromBundle(requireArguments()).city
        daysViewModel.getCityName(name)
        requireActivity().findViewById<TextView>(R.id.textView).text = name

        daysViewModel.list.observe(viewLifecycleOwner,{ result ->
            adapter.differ.submitList(result)
        })

        daysViewModel.weatherMode.observe(viewLifecycleOwner, { state ->
            binding.image.setImageResource(
            when(state){
                "Rainy" -> R.drawable.rainy
                "Cloudy" -> R.drawable.cloudy
                else -> R.drawable.sunny
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