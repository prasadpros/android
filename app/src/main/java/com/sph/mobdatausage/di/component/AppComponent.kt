package com.sph.mobdatausage.di.component

import com.sph.mobdatausage.di.module.ApiModule
import com.sph.mobdatausage.network.MobDataConsumptionApi
import dagger.Component

@Component(modules = [ApiModule::class])
interface AppComponent {

    fun getMobNetConsumptionApi(): MobDataConsumptionApi
}
