package com.example.inovecassignment.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.inovecassignment.databinding.CityItemBinding
import com.example.inovecassignment.models.CityItem
import com.example.inovecassignment.ui.fragments.HomeFragmentDirections

class CityListAdapter: RecyclerView.Adapter<CityListAdapter.StateListViewHolder>() {

    inner class StateListViewHolder(val binding: CityItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StateListViewHolder {
            val adapterLayout = CityItemBinding.inflate(
                LayoutInflater
                    .from(parent.context)
                , parent, false)

        return StateListViewHolder(adapterLayout)
    }

    private val differCallback = object: DiffUtil.ItemCallback<CityItem>(){
        override fun areItemsTheSame(oldItem: CityItem, newItem: CityItem): Boolean {
            return newItem.serialNumber == oldItem.serialNumber
        }

        override fun areContentsTheSame(oldItem: CityItem, newItem: CityItem): Boolean {
            return newItem == oldItem
        }

    }

    var differ = AsyncListDiffer(this, differCallback)

    override fun onBindViewHolder(holder: CityListAdapter.StateListViewHolder, position: Int) {
        val item = differ.currentList[position]

        holder.binding.apply {
            card.setOnClickListener {
                root.findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToDaysFragment(item.name)
                )
            }
            srNo.text = item.serialNumber.toString()
            cityName.text = item.name
            weatherDescription.text = item.description
        }

    }

    override fun getItemCount(): Int = differ.currentList.size



}