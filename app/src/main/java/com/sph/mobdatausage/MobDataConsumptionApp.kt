package com.sph.mobdatausage

import android.app.Application
import com.sph.mobdatausage.di.component.AppComponent
import com.sph.mobdatausage.di.component.DaggerAppComponent
import com.sph.mobdatausage.di.module.AppModule

class MobDataConsumptionApp : Application() {

    companion object {
        lateinit var instance: MobDataConsumptionApp
    }

    override fun onCreate() {
        super.onCreate()
        setupDagger()
        instance = this
    }

    private fun setupDagger(): AppComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()
}
