package com.googy.googleearthview.base.presentation.modules

import com.googy.googleearthview.main.presentation.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        MainViewModel(get())
    }
}
