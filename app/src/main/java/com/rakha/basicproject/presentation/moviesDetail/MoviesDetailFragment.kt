package com.rakha.basicproject.presentation.moviesDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.rakha.basicproject.R
import com.rakha.basicproject.databinding.FragmentMoviesDetailBinding
import com.rakha.basicproject.presentation.base.BaseFragment
import com.rakha.basicproject.utils.GlideApp
import com.rakha.basicproject.utils.generateImageUrl
import com.rakha.basicproject.utils.loadImageFromUrl
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesDetailFragment: BaseFragment() {
    private lateinit var binding: FragmentMoviesDetailBinding
    private val args by navArgs<MoviesDetailFragmentArgs>()
    private val moviesDetailViewModel: MoviesDetailViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMoviesDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun initView() {
        binding.apply {
            setupToolbar(args.movie.title?:"", toolbar)
            toolbar.setNavigationOnClickListener {
                navController.navigateUp()
            }
            tvTitle.text = args.movie.originalTitle
            tvOverview.text = args.movie.overview
            tvRelease.text = args.movie.releaseDate
            ratingBar.rating = args.movie.voteAverage?.div(2) ?: 0f
            ivBackdrop.loadImageFromUrl(generateImageUrl(args.movie.backdropPath?:""))
        }

        moviesDetailViewModel.isFavorite.observe(viewLifecycleOwner) { isFavorite ->
            val imgRes = if(isFavorite) R.drawable.ic_heart_red else R.drawable.ic_heart_grey
            GlideApp
                .with(this@MoviesDetailFragment)
                .load(imgRes)
                .into(binding.ivFavorite)

            binding.ivFavorite.setOnClickListener {
                if(isFavorite) {
                    moviesDetailViewModel.deleteFavorite(args.movie.id?:0)
                } else {
                    moviesDetailViewModel.addFavorite(args.movie)
                }
            }
        }

        moviesDetailViewModel.checkFavorite(args.movie.id?:0)
    }
}