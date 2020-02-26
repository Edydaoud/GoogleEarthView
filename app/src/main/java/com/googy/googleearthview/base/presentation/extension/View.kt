package com.googy.googleearthview.base.presentation.extension

import android.graphics.drawable.ColorDrawable
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

fun View.show() {
    if (visibility != View.VISIBLE) visibility = View.VISIBLE
}

fun View.hide() {
    if (visibility != View.GONE) visibility = View.GONE
}

fun ImageView.imageCenterCrop(
    imageUrl: String,
    thumbUrl: String,
    primaryColor: List<Int>
) {
    val options = RequestOptions()
        .centerCrop()
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .priority(Priority.HIGH)

    val optionsThumb = RequestOptions()
        .centerCrop()
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .priority(Priority.IMMEDIATE)

    Glide.with(context)
        .load(imageUrl)
        .placeholder(ColorDrawable(primaryColor.rgbToInt()))
        .thumbnail(Glide.with(this).load(thumbUrl).apply(optionsThumb))
        .apply(options)
        .into(this)
}



