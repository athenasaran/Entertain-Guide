package com.athena.entertainguide.ui.initial

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.athena.entertainguide.R
import com.athena.entertainguide.databinding.FragmentInitialBinding
import com.athena.entertainguide.entity.PopularEntities
import com.athena.entertainguide.entity.TopRatedEntities
import com.athena.entertainguide.ui.base.BaseFragment
import com.athena.entertainguide.ui.infomovie.InfoMovieFragment
import com.athena.entertainguide.ui.initial.popular.PopularViewAdapter
import com.athena.entertainguide.ui.initial.state.PopularMovieState
import com.athena.entertainguide.ui.initial.state.TopRatedMovieState
import com.athena.entertainguide.ui.initial.topRated.TopRatedViewAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class InitialFragment : BaseFragment<FragmentInitialBinding>() {

    override val binding: FragmentInitialBinding by lazy {
        FragmentInitialBinding.inflate(layoutInflater)
    }

    private val viewModel: InitialViewModel by viewModel()

    private lateinit var topRatedViewAdapter: TopRatedViewAdapter
    private lateinit var popularViewAdapter: PopularViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupInfoHeader()
        viewModel.fetchPopularMovieItems()
        viewModel.fetchTopRatedMovie()
        setupObservers()
    }

    private fun setupInfoHeader() = with(binding.clHeader) {
        title = this@InitialFragment.getString(R.string.home)
        colorText = R.color.white
        color = R.color.purple
        firstIconButton = R.drawable.ic_app
    }

    private fun setupObservers() {
        setupObserverPopularMovieState()
        setupObserverTopRatedMovieState()
    }

    private fun setupObserverPopularMovieState() {
        viewModel.statePopularMovie.bind {
            when (it) {
                is PopularMovieState.Success -> configureSuccessStatePopularMovie(it.entities)
                is PopularMovieState.Error -> configureError(it.exception)
                is PopularMovieState.Loading -> configureLoading(true)
            }
        }
    }

    private fun setupObserverTopRatedMovieState() {
        viewModel.stateTopRatedMovie.bind {
            when (it) {
                is TopRatedMovieState.Success -> configureSuccessStateTopRatedMovie(it.entities)
                is TopRatedMovieState.Error -> configureError(it.exception)
                is TopRatedMovieState.Loading -> configureLoading(true)
            }
        }
    }

    private fun configureSuccessStatePopularMovie(popularEntities: PopularEntities) = with(binding) {
        configureLoading(false)
        setupRecyclerViewPopularMovie(popularEntities)
    }

    private fun configureSuccessStateTopRatedMovie(topRatedEntities: TopRatedEntities) = with(binding) {
        configureLoading(false)
        setupRecyclerViewTopRatedMovie(topRatedEntities)
    }

    private fun configureError(exception: Exception) {
        Toast.makeText(requireContext(), "ERROR $exception", Toast.LENGTH_LONG).show()
    }

    private fun setupRecyclerViewPopularMovie(popularEntities: PopularEntities) = with(binding) {
        rvPopularMovies.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.HORIZONTAL,
            false
        )

        popularViewAdapter = PopularViewAdapter(popularEntities.resultList.toMutableList())
        rvPopularMovies.adapter = popularViewAdapter
        popularViewAdapter.onClick = { view, _ ->
            gotoFragment(InfoMovieFragment.newInstance(view.id))
        }
    }

    private fun setupRecyclerViewTopRatedMovie(topRatedEntities: TopRatedEntities) = with(binding) {
        rvRatedMovies.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.HORIZONTAL,
            false
        )

        topRatedViewAdapter = TopRatedViewAdapter(topRatedEntities.resultList.toMutableList())

        rvRatedMovies.adapter = topRatedViewAdapter

        topRatedViewAdapter.onClick = { view, _ ->
            gotoFragment(InfoMovieFragment.newInstance(view.id))
        }
    }

    private fun gotoFragment(fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun configureLoading(isLoading: Boolean) = with(binding) {
        svInitial.isVisible = !isLoading
        piLoadingEntertainment.isVisible = isLoading
    }

    companion object {
        fun newInstance() = InitialFragment()
    }
}