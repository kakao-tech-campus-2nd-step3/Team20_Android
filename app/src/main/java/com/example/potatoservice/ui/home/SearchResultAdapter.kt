package com.example.potatoservice.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.potatoservice.databinding.ServiceItemBinding
import com.example.potatoservice.ui.share.AdapterCallback
import com.example.potatoservice.ui.share.Volunteer

class SearchResultAdapter(
	private val callback: AdapterCallback
): ListAdapter<Volunteer, SearchResultAdapter.ViewHolder>(
	object : DiffUtil.ItemCallback<Volunteer>(){
		override fun areItemsTheSame(oldItem: Volunteer, newItem: Volunteer): Boolean {
			return oldItem === newItem
		}

		override fun areContentsTheSame(oldItem: Volunteer, newItem: Volunteer): Boolean {
			return oldItem == newItem
		}
	}
) {
	private lateinit var binding: ServiceItemBinding
	inner class ViewHolder(
		itemView: View
	) : RecyclerView.ViewHolder(itemView) {

		init {
			itemView.setOnClickListener {
				callback.onClicked(getItem(adapterPosition))
			}
		}
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		val inflater = LayoutInflater.from(parent.context)
		binding = ServiceItemBinding.inflate(inflater, parent, false)
		return ViewHolder(binding.root)
	}

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		val volunteer: Volunteer = getItem(position)
		binding.volunteer = volunteer
	}
}