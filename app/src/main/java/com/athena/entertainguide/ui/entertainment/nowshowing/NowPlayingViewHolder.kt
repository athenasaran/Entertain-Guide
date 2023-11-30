package com.athena.entertainguide.ui.entertainment.nowshowing

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
        nowShowingImage.loadImageUrl(binding.root.context, resultList.posterPath, R.drawable.image_placeholder)
        nowShowingTitle.text = resultList.title
        nowShowingAverage.text = resultList.voteAverage.toString()
        nowShowingReleaseDate.text = resultList.releaseDate

    }

    private fun setupListeners(onClick: ((ResultItemNowPlaying, Int) -> Unit)?, item: ResultItemNowPlaying, position: Int) =
        with(binding) {
            nowShowingContainer.setOnClickListener {
                onClick?.invoke(item, position)
            }
        }
}