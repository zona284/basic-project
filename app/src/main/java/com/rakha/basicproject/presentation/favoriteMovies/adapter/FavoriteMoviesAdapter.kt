package com.rakha.basicproject.presentation.favoriteMovies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rakha.basicproject.core.domain.model.Movies
import com.rakha.basicproject.databinding.ItemListMoviesBinding
import com.rakha.basicproject.presentation.viewholder.MoviesViewHolder

/**
 *   Created By rakha
 *   29/10/22
 */
class FavoriteMoviesAdapter(private val itemClicked: (movie: Movies) -> Unit = {}) : RecyclerView.Adapter<MoviesViewHolder>() {
    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val item = differ.currentList[position]
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

    private val differ = AsyncListDiffer(this, COMPARATOR)

    override fun getItemCount(): Int = differ.currentList.size

    fun submitData(value: List<Movies>?) = differ.submitList(value)
}