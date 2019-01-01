package com.sph.mobdatausage

import android.app.Application

class MobDataConsumptionApp : Application() {

    companion object {
        lateinit var instance: MobDataConsumptionApp
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

    }
}
