package com.googy.googleearthview.base.presentation.modules

import com.googy.googleearthview.main.data.repository.ImageDataRepositoryImpl
import com.googy.googleearthview.main.domain.repository.ImageDataRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<ImageDataRepository> { ImageDataRepositoryImpl(get()) }
}

