package com.example.inovecassignment.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.inovecassignment.R
import com.example.inovecassignment.adapters.CityListAdapter
import com.example.inovecassignment.constants.LoadingStates.*
import com.example.inovecassignment.databinding.FragmentHomeBinding
import com.example.inovecassignment.viewmodel.HomeViewModel

class HomeFragment: Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var adapter: CityListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

       /* requireActivity().onBackPressed() = {

        }
*/
        binding = FragmentHomeBinding.inflate(layoutInflater)
        homeViewModel = HomeViewModel()
        adapter = CityListAdapter()


        binding.apply {
            viewModel = homeViewModel
            lifecycleOwner = viewLifecycleOwner
            recyclerView.adapter = adapter
        }


        homeViewModel.dataset.observe(viewLifecycleOwner, { result ->
            adapter.differ.submitList(result)
        })


        homeViewModel.loadingStates.observe(viewLifecycleOwner, { state ->
            when(state){
                LOADING -> showProgressBar()
                SUCCESS -> hideProgressBar()
                ERROR -> hideProgressBar().also {
                    Toast.makeText(context, getString(R.string.error_text), Toast.LENGTH_SHORT).show()
                }
                else -> {}
            }
        })


        return binding.root
    }

    private fun showProgressBar(){
        binding.progress.visibility = VISIBLE
    }

    private fun hideProgressBar(){
        binding.progress.visibility = GONE
    }
}