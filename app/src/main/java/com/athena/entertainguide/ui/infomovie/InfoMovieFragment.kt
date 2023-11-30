package com.athena.entertainguide.ui.infomovie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.core.view.isVisible
import com.athena.entertainguide.R
import com.athena.entertainguide.databinding.FragmentInfoMovieBinding
import com.athena.entertainguide.entity.MovieInfoEntities
import com.athena.entertainguide.ui.base.BaseFragment
import com.athena.entertainguide.ui.initial.state.InfoMovieState
import com.athena.entertainguide.utils.glide.loadImageUrl
import org.koin.android.ext.android.inject

class InfoMovieFragment : BaseFragment<FragmentInfoMovieBinding>() {

    override val binding: FragmentInfoMovieBinding by lazy {
        FragmentInfoMovieBinding.inflate(layoutInflater)
    }

    private val viewModel: InfoMovieViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchPopularMovieItems(arguments?.getInt(MOVIE_ID) ?: 238)
        setupObserverInfoMovieState()
        setupOnPressBack()
    }

    private fun setupObserverInfoMovieState() {
        viewModel.stateInfoMovie.bind {
            when (it) {
                is InfoMovieState.Success -> configureSuccessStateInfoMovie(it.entities)
                is InfoMovieState.Error -> configureError(it.exception)
                is InfoMovieState.Loading -> configureLoading(true)
            }
        }
    }

    private fun configureSuccessStateInfoMovie(movieInfoEntities: MovieInfoEntities) = with(binding) {
        configureLoading(false)
        setupInfo(movieInfoEntities)
    }

    private fun setupInfo(movieInfoEntities: MovieInfoEntities) = with(binding) {
        titleInfoMovie.text = movieInfoEntities.title
        releaseDateInfoMovie.text = movieInfoEntities.releaseDate
        runtimeInfoMovie.text = movieInfoEntities.runtime.toString()
        averageInfoMovie.text = getString(R.string.average_info_movie).format(movieInfoEntities.voteAverage)
        overviewInfoMovie.text = movieInfoEntities.overview
        imageInfoMovie.loadImageUrl(requireContext(), movieInfoEntities.backdropPath ?: String(), R.drawable.image_placeholder)
    }

    private fun configureLoading(isLoading: Boolean) = with(binding) {
        infoMovieContainer.isVisible = !isLoading
        loading.isVisible = isLoading
    }

    private fun configureError(exception: Exception) {
        Toast.makeText(requireContext(), "ERROR $exception", Toast.LENGTH_LONG).show()
    }

    private fun setupOnPressBack() {
        requireActivity().onBackPressedDispatcher.addCallback(this@InfoMovieFragment) {
            parentFragmentManager.popBackStackImmediate()
        }
    }

    companion object {
        private const val MOVIE_ID = "MOVIE_ID"

        fun newInstance(movieId: Int) = InfoMovieFragment().apply {
            arguments = Bundle().apply {
                putInt(MOVIE_ID, movieId)
            }
        }
    }
}