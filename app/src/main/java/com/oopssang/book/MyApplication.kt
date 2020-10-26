package com.oopssang.book

import android.app.Application
import com.kakao.sdk.common.KakaoSdk
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

        KakaoSdk.init(this, "{0cb9baec6026922e4ae87354751a2605}")

        startKoin {
            androidContext(this@MyApplication)
            modules(appModule)
        }

    }
}
