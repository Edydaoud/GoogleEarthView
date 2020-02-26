package com.googy.googleearthview.main.presentation.activity

import android.os.Bundle
import com.googy.googleearthview.base.presentation.activity.BaseActivity
import com.googy.googleearthview.base.presentation.extension.addFragment
import com.googy.googleearthview.base.presentation.extension.newFragment
import com.googy.googleearthview.main.presentation.fragment.MainFragment


class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addFragment(newFragment<MainFragment>())
    }
}
