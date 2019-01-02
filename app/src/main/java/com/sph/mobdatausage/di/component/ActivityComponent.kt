package com.sph.mobdatausage.di.component

import com.sph.mobdatausage.di.module.ActivityModule
import com.sph.mobdatausage.di.scope.ActivityScope
import com.sph.mobdatausage.features.home.MobDataConsumptionActivity
import dagger.Component

@Component(dependencies = [AppComponent::class], modules = [ActivityModule::class])
@ActivityScope
interface ActivityComponent {

    fun inject(mobDataConsumptionActivity: MobDataConsumptionActivity)
}
