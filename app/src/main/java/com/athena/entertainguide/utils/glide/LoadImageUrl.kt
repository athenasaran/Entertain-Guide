package com.athena.entertainguide.utils.glide

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestListener

fun ImageView.loadImageUrl(
    context: Context,
    url: String,
    @DrawableRes placeholder: Int
) {
    val baseUrl = "http://image.tmdb.org/t/p/w500"

    post {
        Glide.with(context)
            .load(baseUrl + url)
            .error(placeholder)
            .into(this)
    }
}

fun ImageView.loadImageWithExternalListener(
    url: String,
    @DrawableRes errorResource: Int? = null,
    externalListener: RequestListener<Drawable?>? = null
) {
    post {
        Glide.with(context)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .error(errorResource)
            .listener(externalListener)
            .into(this)
    }
}