package com.rakha.basicproject.presentation.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.rakha.basicproject.core.domain.model.Movies
import com.rakha.basicproject.databinding.ItemListMoviesBinding
import com.rakha.basicproject.utils.generateImageUrl
import com.rakha.basicproject.utils.loadImageFromUrl

/**
 *   Created By rakha
 *   28/10/22
 */
class MoviesViewHolder(
    private val binding: ItemListMoviesBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bindData(movies: Movies?, itemClicked: (movie: Movies) -> Unit = {}) {
        movies?.let {
            binding.apply {
                tvTitle.text = it.title
                ivBackdrop.loadImageFromUrl(generateImageUrl(it.backdropPath?:""))
            }
            binding.cvMovie.setOnClickListener {
                itemClicked(movies)
            }
        }
    }
}