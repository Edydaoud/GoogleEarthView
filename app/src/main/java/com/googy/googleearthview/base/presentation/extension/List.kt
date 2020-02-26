package com.googy.googleearthview.base.presentation.extension

import android.graphics.Color

fun List<Any>.rgbToInt() = Color.parseColor(
    String.format(
        "#%02x%02x%02x%02x",
        this[3],
        this[0],
        this[1],
        this[2]
    )
)