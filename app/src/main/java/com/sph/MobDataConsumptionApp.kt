package com.sph

import android.app.Application
import com.sph.mobdatausage.di.component.AppComponent
import com.sph.mobdatausage.di.component.DaggerAppComponent
import com.sph.mobdatausage.di.module.AppModule
import timber.log.Timber

class MobDataConsumptionApp : Application() {
    lateinit var component: AppComponent

    companion object {
        lateinit var instance: MobDataConsumptionApp
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        component = setupDagger()
        Timber.plant(Timber.DebugTree())

    }

    private fun setupDagger(): AppComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()
}
