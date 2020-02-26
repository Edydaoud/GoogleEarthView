package com.googy.googleearthview.base.presentation.modules

import com.googy.googleearthview.main.domain.usecase.GetImageDataUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { GetImageDataUseCase(get()) }
}