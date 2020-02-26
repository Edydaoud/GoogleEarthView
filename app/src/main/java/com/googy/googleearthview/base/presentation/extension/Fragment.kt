package com.googy.googleearthview.base.presentation.extension

import android.content.Intent
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.googy.googleearthview.R

inline fun <reified F : Fragment> Fragment.addFragment(
    backStack: Boolean = false,
    vararg params: Pair<String, Any>
) {
    fragmentManager?.beginTransaction()?.apply {
        replace(R.id.container, newFragment<F>(*params), F::class.java.canonicalName)
        if (backStack) addToBackStack(F::class.java.canonicalName)
        commit()
    }
}


fun Fragment.shareLink(link: String) {
    val sendIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, link)
        type = "text/plain"
    }

    val shareIntent = Intent.createChooser(sendIntent, null)
    startActivity(shareIntent)
}


inline fun <reified T : Fragment> newFragment(vararg params: Pair<String, Any>): T {
    return T::class.java.newInstance().apply {
        arguments = bundleOf(*params)
    }
}

inline fun <reified T> Fragment.args(key: String, default: T) = lazy {
    (arguments?.get(key) ?: default) as T
}
