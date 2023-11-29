package com.athena.entertainguide.ui.initial.topRated

import androidx.recyclerview.widget.RecyclerView
import com.athena.entertainguide.R
import com.athena.entertainguide.databinding.VhCardViewTopRatedBinding
import com.athena.entertainguide.entity.ResultItemTopRated

internal class TopRatedCardViewHolder(val binding: VhCardViewTopRatedBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(resultList: ResultItemTopRated, onClick: ((ResultItemTopRated, Int) -> Unit)?, position: Int) {
        setupListeners(onClick, resultList, position)
        setupContent(resultList)
    }

    private fun setupContent(resultList: ResultItemTopRated) = with(binding) {
        cardView.apply {
            imageCard = resultList.posterPath
            title = resultList.title
            textColorTag = R.color.black
            colorTitle = R.color.white
            titleTag = resultList.voteAverage.toString()
            iconTag = R.drawable.ic_star
            colorBackgroundTag = R.color.white
        }
    }

    private fun setupListeners(onClick: ((ResultItemTopRated, Int) -> Unit)?, item: ResultItemTopRated, position: Int) =
        with(binding) {
            cardView.setOnClickListener {
                onClick?.invoke(item, position)
            }
        }
}