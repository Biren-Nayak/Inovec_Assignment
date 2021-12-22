package com.example.inovecassignment.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.inovecassignment.databinding.DateItemBinding
import com.example.inovecassignment.models.DayModel

class DateListAdapter: RecyclerView.Adapter<DateListAdapter.StateListViewHolder>() {

    inner class StateListViewHolder(val binding: DateItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StateListViewHolder {
            val adapterLayout = DateItemBinding.inflate(
                LayoutInflater
                    .from(parent.context)
                , parent, false)

        return StateListViewHolder(adapterLayout)
    }

    private val differCallback = object: DiffUtil.ItemCallback<DayModel>(){
        override fun areItemsTheSame(oldItem: DayModel, newItem: DayModel): Boolean {
            return newItem.index == oldItem.index
        }

        override fun areContentsTheSame(oldItem: DayModel, newItem: DayModel): Boolean {
            return newItem == oldItem
        }

    }

    var differ = AsyncListDiffer(this, differCallback)

    override fun onBindViewHolder(holder: DateListAdapter.StateListViewHolder, position: Int) {
        val item = differ.currentList[position]

        holder.binding.apply {
            card.setOnClickListener {

            }
            date.text = item.date
            weatherDescription.text = item.description
            temperature.text = item.temperature
        }

    }

    override fun getItemCount(): Int = differ.currentList.size

}