package com.athena.entertainguide.ui.initial.topRated

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.athena.entertainguide.databinding.VhCardViewTopRatedBinding
import com.athena.entertainguide.entity.ResultItemTopRated

internal class TopRatedViewAdapter(private var mutableList: MutableList<ResultItemTopRated>) :
    RecyclerView.Adapter<TopRatedCardViewHolder>() {

    var onClick: ((ResultItemTopRated, Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopRatedCardViewHolder {
        val view = VhCardViewTopRatedBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TopRatedCardViewHolder(view)
    }

    override fun getItemCount(): Int = mutableList.size

    override fun onBindViewHolder(holder: TopRatedCardViewHolder, position: Int) {
        holder.bind(mutableList[position], onClick, position)
    }

    private companion object : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }
}