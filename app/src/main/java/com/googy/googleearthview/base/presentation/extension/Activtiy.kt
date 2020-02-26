package com.googy.googleearthview.base.presentation.extension

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import com.googy.googleearthview.R
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


fun FragmentActivity.addFragment(
    fragment: Fragment,
    backStack: Boolean = false
) {
    supportFragmentManager.beginTransaction().apply {
        replace(R.id.container, fragment, fragment::class.java.canonicalName)
        if (backStack) addToBackStack(fragment::class.java.canonicalName)
        commit()
    }
}

inline fun <reified T> FragmentActivity.intent(key: String, default: T) = lazy {
    val value = (intent?.extras?.get(key) ?: default)
    return@lazy when {
        default is Boolean && value is String -> {
            value.toBoolean() as T
        }
        else -> {
            value as T
        }
    }
}

fun <T> FragmentActivity.throttleLatest(
    destinationFunction: (T) -> Unit
): (T) -> Unit {
    var throttleJob: Job? = null
    var latestParam: T
    return { param: T ->
        latestParam = param
        if (throttleJob?.isCompleted != false) {
            throttleJob = lifecycleScope.launch {
                delay(300L)
                latestParam.let(destinationFunction)
            }
        }
    }
}
