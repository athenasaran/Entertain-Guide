package com.athena.entertainguide.ui.entertainment.nowshowing

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.athena.entertainguide.databinding.VhNowPlayingBinding
import com.athena.entertainguide.entity.ResultItemNowPlaying

internal class NowPlayingViewAdapter(private var mutableList: MutableList<ResultItemNowPlaying>) :
    RecyclerView.Adapter<NowPlayingViewHolder>() {

    var onClick: ((ResultItemNowPlaying, Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NowPlayingViewHolder {
        val view = VhNowPlayingBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return NowPlayingViewHolder(view)
    }

    override fun getItemCount(): Int = mutableList.size

    override fun onBindViewHolder(holder: NowPlayingViewHolder, position: Int) {
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