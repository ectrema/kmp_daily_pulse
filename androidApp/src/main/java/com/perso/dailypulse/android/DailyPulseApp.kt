package com.perso.dailypulse.android

import android.app.Application
import com.perso.dailypulse.android.di.viewModelModule
import com.perso.dailypulse.di.sharedKoinModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DailyPulseApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initMain()
    }

    private fun initMain() {
        val module = sharedKoinModule + viewModelModule

        startKoin {
            androidContext(this@DailyPulseApp)
            modules(module)
        }
    }

}