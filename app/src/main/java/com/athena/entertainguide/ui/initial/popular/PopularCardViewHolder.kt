package com.athena.entertainguide.ui.initial.popular

import androidx.recyclerview.widget.RecyclerView
import com.athena.entertainguide.R
import com.athena.entertainguide.databinding.VhCardViewPopularBinding
import com.athena.entertainguide.entity.ResultItemPopular

internal class PopularCardViewHolder(val binding: VhCardViewPopularBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(resultList: ResultItemPopular, onClick: ((ResultItemPopular, Int) -> Unit)?, position: Int) {
        setupListeners(onClick, resultList, position)
        setupContent(resultList)
    }

    private fun setupContent(resultList: ResultItemPopular) = with(binding) {
        cvCardViewPopular.apply {
            imageCard = resultList.posterPath
            title = resultList.title
            textColorTag = R.color.black
            colorTitle = R.color.white
            titleTag = resultList.voteAverage.toString()
            iconTag = R.drawable.ic_star
            colorBackgroundTag = R.color.white
        }
    }

    private fun setupListeners(onClick: ((ResultItemPopular, Int) -> Unit)?, item: ResultItemPopular, position: Int) =
        with(binding) {
            cvCardViewPopular.setOnClickListener {
                onClick?.invoke(item, position)
            }
        }
}