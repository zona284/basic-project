package com.rakha.basicproject.presentation.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.rakha.basicproject.R
import com.rakha.basicproject.databinding.FragmentSplashBinding
import com.rakha.basicproject.presentation.base.BaseFragment
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment : BaseFragment() {
    private lateinit var binding: FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun initView() {
        val animation = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in)

        lifecycleScope.launch{
            binding.tvSplash.startAnimation(animation)
            delay(2000)
            findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToMoviesFragment())
        }
    }
}