package com.rakha.basicproject.presentation.movies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.rakha.basicproject.core.domain.model.Movies
import com.rakha.basicproject.databinding.ItemListMoviesBinding
import com.rakha.basicproject.presentation.viewholder.MoviesViewHolder

/**
 *   Created By rakha
 *   28/10/22
 */
class MoviesPagingAdapter(
    private val itemClicked: (movie: Movies) -> Unit = {}
) : PagingDataAdapter<Movies, MoviesViewHolder>(COMPARATOR) {

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val item = getItem(position)
        holder.bindData(item, itemClicked)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view = ItemListMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesViewHolder(view)
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Movies>() {
            override fun areItemsTheSame(oldItem: Movies, newItem: Movies): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Movies, newItem: Movies): Boolean {
                return oldItem == newItem
            }
        }
    }
}