package com.athena.entertainguide.component.tabbar.customviews

import android.content.Context
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.athena.entertainguide.R
import com.athena.entertainguide.databinding.TabBarItemBinding

internal class TabBarItemView(context: Context) : LinearLayout(context) {

    private val binding: TabBarItemBinding = TabBarItemBinding.inflate(LayoutInflater.from(context), this, true)

    private val unselectedColor = ContextCompat.getColor(context, R.color.purple_dark)

    private val selectedColor = ContextCompat.getColor(context, R.color.purple)

    override fun setSelected(selected: Boolean) {
        updateItemColor(selected)
    }

    private fun setTitle(title: String) {
        binding.itemText.text = title
    }

    private fun setIcon(@DrawableRes drawableRes: Int) {
        binding.itemIcon.setImageDrawable(ContextCompat.getDrawable(context, drawableRes))
    }

    private fun updateItemColor(selected: Boolean) {
        binding.itemIcon.setColorFilter(if (selected) unselectedColor else selectedColor)
        binding.itemText.setTextColor(if (selected) unselectedColor else selectedColor)
    }

    companion object {

        fun createInstance(context: Context, title: String, @DrawableRes icon: Int) =
            TabBarItemView(context).apply {
                setTitle(title)
                setIcon(icon)
                onFinishInflate()
            }
    }
}