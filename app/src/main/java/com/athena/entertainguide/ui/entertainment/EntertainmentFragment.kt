package com.athena.entertainguide.ui.entertainment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.athena.entertainguide.R
import com.athena.entertainguide.databinding.FragmentEntertainmentBinding
import com.athena.entertainguide.entity.NowPlayingEntities
import com.athena.entertainguide.ui.base.BaseFragment
import com.athena.entertainguide.ui.entertainment.nowshowing.NowPlayingViewAdapter
import com.athena.entertainguide.ui.infomovie.InfoMovieFragment
import com.athena.entertainguide.ui.initial.state.EntertainmentState
import org.koin.android.ext.android.inject

internal class EntertainmentFragment : BaseFragment<FragmentEntertainmentBinding>() {

    override val binding: FragmentEntertainmentBinding by lazy {
        FragmentEntertainmentBinding.inflate(layoutInflater)
    }

    private val viewModel: EntertainmentViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupInfoHeader()
        viewModel.fetchNowPlaying()
        setupObserverPopularMovieState()
    }

    private fun setupObserverPopularMovieState() {
        viewModel.stateEntertainment.bind {
            when (it) {
                is EntertainmentState.Success -> configureSuccessStateEntertainment(it.entities)
                is EntertainmentState.Error -> configureError(it.exception)
                is EntertainmentState.Loading -> configureLoading(true)
            }
        }
    }

    private fun setupInfoHeader() = with(binding.entertainmentHeader) {
        title = this@EntertainmentFragment.getString(R.string.entertainment)
        colorText = R.color.white
        color = R.color.purple
        firstIconButton = R.drawable.ic_app
    }

    private fun configureSuccessStateEntertainment(nowPlayingEntities: NowPlayingEntities) = with(binding) {
        configureLoading(false)
        setupRecyclerViewNowShowingMovie(nowPlayingEntities)
    }

    private fun setupRecyclerViewNowShowingMovie(nowPlayingEntities: NowPlayingEntities) = with(binding) {
        rvSaveList.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.HORIZONTAL,
            false
        )

        val topRatedViewAdapter = NowPlayingViewAdapter(nowPlayingEntities.resultList.toMutableList())

        rvSaveList.adapter = topRatedViewAdapter

        topRatedViewAdapter.onClick = { view, _ ->
            gotoFragment(InfoMovieFragment.newInstance(view.id))
        }
    }

    private fun configureLoading(isLoading: Boolean) = with(binding) {
        svEntertainment.isVisible = !isLoading
        loading.isVisible = isLoading
    }

    private fun configureError(exception: Exception) {
        Toast.makeText(requireContext(), "ERROR $exception", Toast.LENGTH_LONG).show()
    }

    private fun gotoFragment(fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    companion object {
        fun newInstance() = EntertainmentFragment()
    }
}