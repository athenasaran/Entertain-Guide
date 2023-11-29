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

            binding.title.text = field
        }

    /** First icon on left */
    var firstIconButton: Int? = null
        set(value) {
            field = value

            setIconButton(binding.firstIcon, field)
        }

    /** First icon after title */
    var secondIconButton: Int? = null
        set(value) {
            field = value

            setIconButton(binding.secondIcon, field)
        }

    /** Second icon after title */
    var lastIconButton: Int? = null
        set(value) {
            field = value

            setIconButton(binding.lastIcon, field)
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
        binding.firstIcon.setOnClickListener {
            backClicked.invoke()
        }
    }

    fun onSecondIconButtonClicked(firstIconButtonClicked: () -> Unit) {
        binding.secondIcon.setOnClickListener {
            firstIconButtonClicked.invoke()
        }
    }

    fun onLastIconButtonClicked(secondIconButtonClicked: () -> Unit) {
        binding.lastIcon.setOnClickListener {
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
        binding.header.setBackgroundColor(ContextCompat.getColor(context, color ?: R.color.purple))
    }

    private fun setTextColor(color: Int?) {
        binding.title.setTextColor(ContextCompat.getColor(context, color ?: R.color.white))
    }

    private companion object {
        const val NONE: Int = -1
    }
}