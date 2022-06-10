package com.example.notist

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext
import org.koin.core.logger.Level

//when application loads it will call this class and start koin context
class NotistApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        GlobalContext.startKoin{
            androidLogger(if(BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@NotistApplication)
            modules(appModule)
            //initiate the viewModel
        }
    }
}