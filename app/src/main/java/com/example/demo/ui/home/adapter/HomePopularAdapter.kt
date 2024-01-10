package com.example.demo.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.demo.databinding.ItemHomePopularBinding
import com.example.demo.ui.home.PopularUiState


class HomePopularAdapter() :
    ListAdapter<PopularUiState, HomePopularAdapter.HomePopularViewHolder>(
        HomeCategoryDiffUtil()
    ) {

    class HomePopularViewHolder(private val itemHomePopularBinding: ItemHomePopularBinding ) :
        RecyclerView.ViewHolder(itemHomePopularBinding.root) {
        fun bind(popularUiState: PopularUiState) {
            itemHomePopularBinding.item = popularUiState
            itemHomePopularBinding.executePendingBindings()
        }
    }

    class HomeCategoryDiffUtil : DiffUtil.ItemCallback<PopularUiState>() {
        override fun areItemsTheSame(oldItem: PopularUiState, newItem: PopularUiState): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PopularUiState, newItem: PopularUiState): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomePopularViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return HomePopularViewHolder(
            ItemHomePopularBinding.inflate(
                layoutInflater,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HomePopularViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}