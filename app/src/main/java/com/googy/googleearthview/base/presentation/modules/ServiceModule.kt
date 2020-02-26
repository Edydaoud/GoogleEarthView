package com.googy.googleearthview.base.presentation.modules

import com.googy.googleearthview.main.data.service.ImageDataApiService
import org.koin.dsl.module

val serviceModule = module {
    single { ImageDataApiService(get()) }
}