package com.googy.googleearthview.base.presentation.extension

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> LiveData<T>.observe(
    baseFragment: Fragment,
    block: (T) -> Unit
) {
    observe(baseFragment, Observer(block))
}

