package com.rakha.basicproject

import android.app.Application
import com.rakha.core.di.databaseModule
import com.rakha.core.di.networkModule
import com.rakha.core.di.repositoryModule
import com.rakha.basicproject.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

/**
 *   Created By rakha
 *   26/10/22
 */
class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MainApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    viewModelModule
                )
            )
        }
    }
}