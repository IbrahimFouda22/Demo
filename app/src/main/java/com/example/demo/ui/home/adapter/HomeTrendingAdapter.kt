package com.example.demo.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.demo.databinding.ItemCategoryBinding
import com.example.demo.databinding.ItemHomeTrendingBinding
import com.example.demo.ui.home.CategoryUiState
import com.example.demo.ui.home.TrendingUiState


class HomeTrendingAdapter() :
    ListAdapter<TrendingUiState, HomeTrendingAdapter.HomeCategoryViewHolder>(
        HomeCategoryDiffUtil()
    ) {

    class HomeCategoryViewHolder(private val itemHomeTrendingBinding: ItemHomeTrendingBinding ) :
        RecyclerView.ViewHolder(itemHomeTrendingBinding.root) {
        fun bind(trendingUiState: TrendingUiState) {
            itemHomeTrendingBinding.item = trendingUiState
            itemHomeTrendingBinding.executePendingBindings()
        }
    }

    class HomeCategoryDiffUtil : DiffUtil.ItemCallback<TrendingUiState>() {
        override fun areItemsTheSame(oldItem: TrendingUiState, newItem: TrendingUiState): Boolean {
            return oldItem.logo == newItem.logo
        }

        override fun areContentsTheSame(oldItem: TrendingUiState, newItem: TrendingUiState): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeCategoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return HomeCategoryViewHolder(
            ItemHomeTrendingBinding.inflate(
                layoutInflater,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HomeCategoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}