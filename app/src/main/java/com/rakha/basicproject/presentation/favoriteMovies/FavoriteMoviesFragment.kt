package com.rakha.basicproject.presentation.favoriteMovies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.rakha.basicproject.R
import com.rakha.basicproject.core.data.Resource
import com.rakha.basicproject.databinding.FragmentFavoriteMoviesBinding
import com.rakha.basicproject.presentation.base.BaseFragment
import com.rakha.basicproject.presentation.favoriteMovies.adapter.FavoriteMoviesAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel


class FavoriteMoviesFragment : BaseFragment() {

    private lateinit var binding: FragmentFavoriteMoviesBinding
    private val moviesViewModel: FavoriteMoviesViewModel by viewModel()

    private lateinit var moviesAdapter: FavoriteMoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoriteMoviesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun initView() {
        setupToolbar(getString(R.string.favorite), binding.toolbar)
        binding.toolbar.setNavigationOnClickListener {
            navController.navigateUp()
        }

        moviesAdapter = FavoriteMoviesAdapter { data ->
            navController.navigate(FavoriteMoviesFragmentDirections.actionFavoriteMoviesFragmentToMoviesDetailFragment(data))
        }

        binding.rvMovies.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = moviesAdapter
        }

        moviesViewModel.getFavMovies().observe(viewLifecycleOwner) {
            if(it is Resource.Success) {
                moviesAdapter.submitData(it.data)
                binding.lyEmpty.isVisible = it.data?.isEmpty() == true
                binding.btnBack.setOnClickListener { navController.navigateUp() }
            }
        }
    }
}