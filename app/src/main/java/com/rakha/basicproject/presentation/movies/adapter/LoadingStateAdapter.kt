package com.rakha.basicproject.presentation.movies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rakha.basicproject.R
import com.rakha.basicproject.databinding.ItemLoadingStateBinding

/**
 *   Created By rakha
 *   28/10/22
 */
class LoadingStateAdapter(private val retryCallback: () -> Unit = {}) : LoadStateAdapter<LoadingStateAdapter.LoadingStateViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState) =
        LoadingStateViewHolder(
            ItemLoadingStateBinding.bind(
                LayoutInflater.from(parent.context).inflate(R.layout.item_loading_state, parent, false)
            )
        )

    override fun onBindViewHolder(holder: LoadingStateViewHolder, loadState: LoadState) =
        holder.bind(loadState)

    inner class LoadingStateViewHolder(
        private val binding: ItemLoadingStateBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.btnRetry.setOnClickListener { retryCallback.invoke() }
        }

        fun bind(loadState: LoadState) {
            with(binding) {
                progressBar.isVisible = loadState is LoadState.Loading
                btnRetry.isVisible = loadState is LoadState.Error
                tvMessage.isVisible =
                    !(loadState as? LoadState.Error)?.error?.message.isNullOrBlank()
                tvMessage.text = (loadState as? LoadState.Error)?.error?.message
            }
        }
    }
}