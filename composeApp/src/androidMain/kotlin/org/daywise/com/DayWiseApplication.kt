package org.daywise.com

import android.app.Application
import com.task.businesslogicshared.di.businessModule
import org.daywise.com.di.AppModule
import org.koin.core.context.startKoin
import org.koin.android.ext.koin.androidContext

class DayWiseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@DayWiseApplication)
            modules(listOf(AppModule.appModule, businessModule))
        }
    }
}
