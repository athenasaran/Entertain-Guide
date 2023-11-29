package com.athena.entertainguide.ui.initial.popular

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.athena.entertainguide.databinding.VhCardViewPopularBinding
import com.athena.entertainguide.entity.ResultItemPopular

internal class PopularViewAdapter(private var mutableList: MutableList<ResultItemPopular>) :
    RecyclerView.Adapter<PopularCardViewHolder>() {

    var onClick: ((ResultItemPopular, Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularCardViewHolder {
        val view = VhCardViewPopularBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PopularCardViewHolder(view)
    }

    override fun getItemCount(): Int = mutableList.size

    override fun onBindViewHolder(holder: PopularCardViewHolder, position: Int) {
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