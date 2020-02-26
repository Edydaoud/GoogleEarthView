package com.googy.googleearthview

import android.app.Application
import com.googy.googleearthview.base.presentation.modules.getModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            androidFileProperties()
            modules(getModules())
        }
    }
}