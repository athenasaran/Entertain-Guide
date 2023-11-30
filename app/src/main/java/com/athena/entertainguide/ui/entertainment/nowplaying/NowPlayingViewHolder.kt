package com.athena.entertainguide.ui.entertainment.nowplaying

import androidx.recyclerview.widget.RecyclerView
import com.athena.entertainguide.R
import com.athena.entertainguide.databinding.VhNowPlayingBinding
import com.athena.entertainguide.entity.ResultItemNowPlaying
import com.athena.entertainguide.utils.glide.loadImageUrl

internal class NowPlayingViewHolder(val binding: VhNowPlayingBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(resultList: ResultItemNowPlaying, onClick: ((ResultItemNowPlaying, Int) -> Unit)?, position: Int) {
        setupListeners(onClick, resultList, position)
        setupContent(resultList)
    }

    private fun setupContent(resultList: ResultItemNowPlaying) = with(binding) {
        ivNowPlaying.loadImageUrl(binding.root.context, resultList.posterPath, R.drawable.image_placeholder)
        tvNowPlayingTitle.text = resultList.title
        tvNowPlayingAverage.text = resultList.voteAverage.toString()
        tvNowPlayingReleaseDate.text = resultList.releaseDate
    }

    private fun setupListeners(onClick: ((ResultItemNowPlaying, Int) -> Unit)?, item: ResultItemNowPlaying, position: Int) =
        with(binding) {
            clNowPlayingContainer.setOnClickListener {
                onClick?.invoke(item, position)
            }
        }
}