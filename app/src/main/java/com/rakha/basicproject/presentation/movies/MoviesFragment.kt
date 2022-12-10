package com.rakha.basicproject.presentation.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.rakha.basicproject.R
import com.rakha.basicproject.databinding.FragmentMoviesBinding
import com.rakha.basicproject.presentation.base.BaseFragment
import com.rakha.basicproject.presentation.movies.adapter.LoadingStateAdapter
import com.rakha.basicproject.presentation.movies.adapter.MoviesPagingAdapter
import com.xwray.groupie.GroupieAdapter
import kotlinx.coroutines.flow.collectLatest
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
                findNavController().navigate(MoviesFragmentDirections.actionMoviesFragmentToFavoriteMoviesFragment())
            }
        }

        moviesAdapter = MoviesPagingAdapter { data ->
            findNavController().navigate(MoviesFragmentDirections.actionMoviesFragmentToMoviesDetailFragment(data))
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

        viewLifecycleOwner.lifecycleScope.launch {
            moviesAdapter.loadStateFlow.collectLatest { loadState ->
                val isListEmpty = loadState.refresh is LoadState.NotLoading && moviesAdapter.itemCount == 0
                // show empty list
                binding.tvEmpty.isVisible = isListEmpty
                // Only show the list if refresh succeeds.
                binding.rvMovies.isVisible = !isListEmpty
                // Show loading spinner during initial load or refresh.
                binding.shimmerLoading.isVisible = loadState.source.refresh is LoadState.Loading
                // Show the retry state if initial load or refresh fails.
                val errorState = loadState.source.refresh as? LoadState.Error
                binding.tvError.apply {
                    isVisible = errorState is LoadState.Error
                    text = errorState?.error.toString()
                }
            }
        }

        val groupAdapter = GroupieAdapter()
        populateData().forEach {
            it.name?.let { name -> groupAdapter.add(HeaderItem(name)) }
            it.step?.map { step -> StepItem(step) }?.let { data ->
                groupAdapter.addAll(data)
            }

        }

        binding.rvSample.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = groupAdapter
        }
    }

    override fun onBackPressed() {
        requireActivity().finish()
    }

    fun populateData(): List<Item> {
        return listOf(
            Item(
                name = "",
                step = listOf(
                    Steps(
                        number = 1,
                        step = "A"
                    ),
                    Steps(
                        number = 2,
                        step = "B"
                    )
                )
            ),
            Item(
                name = "Freak",
                step = listOf(
                    Steps(
                        number = 1,
                        step = "C"
                    ),
                    Steps(
                        number = 2,
                        step = "D"
                    )
                )
            )
        )
    }

    data class Item(
        val name : String? = null,
        val step: List<Steps>? = null
    )

    data class Steps(
        val number: Int? = null,
        val step: String? = null
    )
}