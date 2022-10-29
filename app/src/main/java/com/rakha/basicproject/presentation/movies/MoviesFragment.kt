package com.rakha.basicproject.presentation.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.rakha.basicproject.R
import com.rakha.basicproject.databinding.FragmentMoviesBinding
import com.rakha.basicproject.presentation.base.BaseFragment
import com.rakha.basicproject.presentation.movies.adapter.LoadingStateAdapter
import com.rakha.basicproject.presentation.movies.adapter.MoviesPagingAdapter
import com.rakha.basicproject.utils.GlideApp
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class MoviesFragment : BaseFragment() {
    private lateinit var binding: FragmentMoviesBinding
    private val moviesViewModel: MoviesViewModel by viewModel()

    private lateinit var moviesAdapter: MoviesPagingAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun initView() {
        binding.apply {
            tvPageTitle.text = getString(R.string.app_name)
            ivPageAction.isVisible = true
            ivPageAction.setOnClickListener {
                navController.navigate(MoviesFragmentDirections.actionMoviesFragmentToFavoriteMoviesFragment())
            }
        }

        moviesAdapter = MoviesPagingAdapter { data ->
            navController.navigate(MoviesFragmentDirections.actionMoviesFragmentToMoviesDetailFragment(data))
        }


        binding.rvMovies.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = moviesAdapter.withLoadStateFooter (
                footer = LoadingStateAdapter (moviesAdapter::retry)
            )
        }

        binding.swipeRefresh.setOnRefreshListener {
            moviesAdapter.refresh()
        }

        moviesViewModel.getAllMovies().observe(viewLifecycleOwner) {
            binding.swipeRefresh.isRefreshing = false
            moviesAdapter.submitData(lifecycle, it)
        }
    }

    override fun onBackPressed() {
        requireActivity().finish()
    }
}