package com.athena.entertainguide.component.tag

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.DrawableRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.athena.entertainguide.R
import com.athena.entertainguide.databinding.TagViewBinding

/**
 * A view to categorize some content.
 *
 * It displays a [label] with some color.
 * An optional [icon] can be displayed on the left.
 */
class TagView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : ConstraintLayout(context, attrs, defStyle) {

    private val binding: TagViewBinding by lazy {
        TagViewBinding.inflate(LayoutInflater.from(context), this, true)
    }

    private val defaultColor = R.color.purple


    /** Selects color background of tag. */
    var colorBackground: Int? = null
        set(value) {
            field = value
            setupBackgroundColor(value)
        }

    /** Selects color text of tag. */
    var textColor: Int? = null
        set(value) {
            field = value
            setupTextColor(textColor)
        }

    /** Icon to be displayed on the left or null to remove it. Tinted with the same [label]'s color. */
    @DrawableRes
    var icon: Int? = null
        set(value) {
            field = value
            setupIcon(value)
        }

    /** Label to be displayed on this tag. The value will be used as content description. */
    var label: CharSequence = ""
        set(value) {
            field = value
            setupText(value)
        }

    private fun setupViews() {
        setupBackgroundColor(colorBackground)
        setupIcon(icon)
        setupText(label)
        setupTextColor(textColor)
    }

    private fun setupBackgroundColor(color: Int?): Unit = with(binding) {
        tagContainer.setBackgroundColor(ContextCompat.getColor(context, color ?: defaultColor))
    }

    private fun setupTextColor(color: Int?): Unit = with(binding) {
        tagText.setTextColor(ContextCompat.getColorStateList(context, color ?: defaultColor))
    }

    private fun setupIcon(@DrawableRes icon: Int?): Unit = with(binding.tagIcon) {
        val iconDrawable = icon?.let { ContextCompat.getDrawable(context, it) }
        this.background = iconDrawable
        this.isVisible = iconDrawable != null
    }

    private fun setupText(label: CharSequence): Unit = with(binding) {
        tagText.text = label
        root.contentDescription = label
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        setupViews()
    }
}