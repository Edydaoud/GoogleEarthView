package com.googy.googleearthview.base.presentation.extension

import android.graphics.Bitmap
import android.widget.ImageView
import com.bumptech.glide.Glide
import java.io.File

suspend fun ImageView.getCachedPath(url: String): String {
    val cachedFile = Glide.with(this).asFile().load(url).submit().get()
    return cachedFile.copyTo(File(context.externalCacheDir, cachedFile.name), overwrite = true)
        .absolutePath
}

fun ImageView.getBitmap(url: String): Bitmap {
    return Glide.with(this).asBitmap().load(url).submit().get()
}