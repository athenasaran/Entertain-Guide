package com.athena.entertainguide.component.card

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.athena.entertainguide.R
import com.athena.entertainguide.databinding.CardViewBinding
import com.athena.entertainguide.utils.glide.loadImageWithExternalListener
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import java.lang.ref.WeakReference

/**
 * A card view to show info.
 *
 * It displays a [tag] with some color.
 * It displays a [title] with some color.
 * It displays a [imageCard].
 * Show 4 icons: rate, share, comment and save to list
 */
class CardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : ConstraintLayout(context, attrs, defStyle) {

    private val binding: CardViewBinding by lazy {
        CardViewBinding.inflate(LayoutInflater.from(context), this, true)
    }

    private val defaultColor = R.color.white


    /** Selects color background of tag. */
    var colorBackgroundTag: Int? = null
        set(value) {
            field = value
            setupBackgroundColorTag(value)
        }

    /** Selects color background of tag. */
    var iconTag: Int? = null
        set(value) {
            field = value
            setupIcon(value)
        }

    /** Selects color text of tag. */
    var textColorTag: Int? = null
        set(value) {
            field = value
            setupTextColorTag(value)
        }

    /** Image for card */
    var imageCard: String = String()
        set(value) {
            field = value
            setupImageCard(value)
        }

    /** Title to be displayed on tag. The value will be used as content description. */
    var titleTag: CharSequence = String()
        set(value) {
            field = value
            setupLabelTag(value)
        }

    /** Title to be displayed on top on card */
    var title: CharSequence = String()
        set(value) {
            field = value
            setupTitle(value)
        }

    /** Color of title to be displayed on top on card */

    var colorTitle: Int? = null
        set(value) {
            field = value
            setupColorTitle(value)
        }

    fun setOnClickListenerRate(click: (() -> Unit)) = with(binding.cardViewRate) {
        click.invoke()
    }

    fun setOnClickListenerComment(click: (() -> Unit)) = with(binding.cardViewComment) {
        click.invoke()
    }

    fun setOnClickListenerShared(click: (() -> Unit)) = with(binding.cardViewShare) {
        click.invoke()
    }

    fun setOnClickListenerSaveOnList(click: (() -> Unit)) = with(binding.cardViewSaveList) {
        click.invoke()
    }

    fun setOnClickListenerOnCard(click: (() -> Unit)) = with(binding.cardContainer) {
        click.invoke()
    }

    private fun setupViews() {
        setupBackgroundColorTag(colorBackgroundTag)
        setupImageCard(imageCard)
        setupLabelTag(titleTag.toString())
        setupTitle(title.toString())
        setupTextColorTag(textColorTag)
    }

    private fun setupBackgroundColorTag(color: Int?): Unit = with(binding) {
        cardViewTag.colorBackground = color ?: defaultColor
    }

    private fun setupColorTitle(color: Int?): Unit = with(binding) {
        cardViewTitle.setTextColor(ContextCompat.getColor(context, color ?: defaultColor))
    }

    private fun setupIcon(icon: Int?): Unit = with(binding) {
        cardViewTag.icon = icon
    }

    private fun setupTextColorTag(color: Int?): Unit = with(binding) {
        cardViewTag.textColor = color ?: defaultColor
    }

    private fun setupImageCard(imageCard: CharSequence) {
        val baseUrl = "http://image.tmdb.org/t/p/w500"
        val url = baseUrl + imageCard
        binding.cardViewImage.loadImageWithExternalListener(
            url = url, externalListener = createImageLoadingCallback()
        )
    }

    private fun createImageLoadingCallback() = object : RequestListener<Drawable?> {
        private val bindingRef: WeakReference<CardViewBinding> = WeakReference(binding)

        override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable?>, isFirstResource: Boolean): Boolean {
            bindingRef.get()?.cardViewImage?.setBackgroundResource(R.color.white_neutral)

            return false
        }

        override fun onResourceReady(
            resource: Drawable,
            model: Any,
            target: Target<Drawable?>?,
            dataSource: DataSource,
            isFirstResource: Boolean
        ): Boolean {
            return false
        }
    }

    private fun setupLabelTag(titleTag: CharSequence): Unit = with(binding) {
        cardViewTag.label = titleTag
    }

    private fun setupTitle(titleTag: CharSequence): Unit = with(binding) {
        cardViewTitle.text = titleTag
        root.contentDescription = titleTag
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        setupViews()
    }
}