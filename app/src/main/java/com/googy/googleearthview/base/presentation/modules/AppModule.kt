package com.googy.googleearthview.base.presentation.modules

import com.googy.googleearthview.base.network.provideOkHttpClient
import com.googy.googleearthview.base.network.provideRetrofit
import com.google.gson.Gson
import org.koin.core.module.Module
import org.koin.dsl.module


fun getModules(): List<Module> {
    val appModule = module {
        single { Gson() }
        single { provideOkHttpClient() }
        single { provideRetrofit(get()) }
    }

    return listOf(
        appModule,
        repositoryModule,
        serviceModule,
        useCaseModule,
        viewModelModule
    )
}

