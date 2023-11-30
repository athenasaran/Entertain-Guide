package com.athena.entertainguide.component.header

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.annotation.DrawableRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.athena.entertainguide.R
import com.athena.entertainguide.databinding.HeaderViewBinding

internal class HeaderView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val binding = HeaderViewBinding.inflate(LayoutInflater.from(context), this, true)

    /** Title to header */
    var title: String = ""
        set(value) {
            field = value

            binding.tvTitleHeader.text = field
        }

    /** First icon on left */
    var firstIconButton: Int? = null
        set(value) {
            field = value

            setIconButton(binding.ivFirstIconHeader, field)
        }

    /** First icon after title */
    var secondIconButton: Int? = null
        set(value) {
            field = value

            setIconButton(binding.ivSecondIconHeader, field)
        }

    /** Second icon after title */
    var lastIconButton: Int? = null
        set(value) {
            field = value

            setIconButton(binding.ivLastIconHeader, field)
        }

    /** Color of Header */
    var color: Int? = null
        set(value) {
            field = value
            setBackgroundColor(color)
        }

    /** Color of title of header */
    var colorText: Int? = null
        set(value) {
            field = value
            setTextColor(colorText)
        }

    fun onFirstIconButtonClicked(backClicked: () -> Unit) {
        binding.ivFirstIconHeader.setOnClickListener {
            backClicked.invoke()
        }
    }

    fun onSecondIconButtonClicked(firstIconButtonClicked: () -> Unit) {
        binding.ivSecondIconHeader.setOnClickListener {
            firstIconButtonClicked.invoke()
        }
    }

    fun onLastIconButtonClicked(secondIconButtonClicked: () -> Unit) {
        binding.ivLastIconHeader.setOnClickListener {
            secondIconButtonClicked.invoke()
        }
    }

    private fun setIconButton(iconButton: ImageView, @DrawableRes icon: Int?) {
        iconButton.apply {
            isVisible = if (icon == null || icon == NONE) {
                false
            } else {
                setImageDrawable(AppCompatResources.getDrawable(context, icon))
                true
            }
        }
    }

    private fun setBackgroundColor(color: Int?) {
        binding.clHeader.setBackgroundColor(ContextCompat.getColor(context, color ?: R.color.purple))
    }

    private fun setTextColor(color: Int?) {
        binding.tvTitleHeader.setTextColor(ContextCompat.getColor(context, color ?: R.color.white))
    }

    private companion object {
        const val NONE: Int = -1
    }
}