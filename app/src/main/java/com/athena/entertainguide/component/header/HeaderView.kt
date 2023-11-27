package com.athena.entertainguide.component.header

import android.content.Context
import android.graphics.drawable.ColorDrawable
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

    var title: String = ""
        set(value) {
            field = value

            binding.title.text = field
        }

    var firstIconButton: Int? = null
        set(value) {
            field = value

            setIconButton(binding.firstIcon, field)
        }

    var secondIconButton: Int? = null
        set(value) {
            field = value

            setIconButton(binding.secondIcon, field)
        }

    var lastIconButton: Int? = null
        set(value) {
            field = value

            setIconButton(binding.lastIcon, field)
        }

    var color: Int? = null
        set(value) {
            field = value
            setBackgroundColor(color)
        }

    init {
        val array = context.obtainStyledAttributes(attrs, R.styleable.HeaderView, 0, 0)

        firstIconButton = array.getResourceId(
            R.styleable.HeaderView_firstIcon,
            NONE
        )

        secondIconButton = array.getResourceId(
            R.styleable.HeaderView_secondIcon,
            NONE
        )

        lastIconButton = array.getResourceId(
            R.styleable.HeaderView_lastIcon,
            NONE
        )

        color = array.getResourceId(
            R.styleable.HeaderView_colorHeader,
            NONE
        )

        title = array.getString(
            R.styleable.HeaderView_title
        ).orEmpty()

        array.recycle()
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
        binding.header.background = ColorDrawable(
            ContextCompat.getColor(
                context,
                color ?: R.color.purple
            )
        )
    }

    private companion object {
        const val NONE: Int = -1
    }
}