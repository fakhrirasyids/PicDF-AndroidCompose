package com.fakhrirasyids.picdf

import android.app.Application
import com.fakhrirasyids.picdf.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class PicDF: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@PicDF)
            modules(
                listOf(
                    viewModelModule
                )
            )
        }
    }
}