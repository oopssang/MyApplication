package com.oopssang.book

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MyApplication : Application() {

    val appModule = module {
        single<Repository> { RepositoryImpl() }
        factory { MyPresenter(get()) }
    }
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApplication)
            modules(appModule)
        }

    }
}
