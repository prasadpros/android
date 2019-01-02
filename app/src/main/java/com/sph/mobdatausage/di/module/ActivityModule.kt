package com.sph.mobdatausage.di.module

import com.sph.mobdatausage.di.scope.ActivityScope
import com.sph.mobdatausage.features.home.adapter.MobDataConsumptionAdapter
import com.sph.mobdatausage.rx.AndroidSchedulingStrategyFactory
import com.sph.mobdatausage.rx.SchedulingStrategy
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers

@Module(includes = [AppModule::class])
@ActivityScope
class ActivityModule {

    @Provides
    fun adapter(): MobDataConsumptionAdapter {
        return MobDataConsumptionAdapter()
    }

    @Provides
    fun scheduler(): SchedulingStrategy.Factory {
        return AndroidSchedulingStrategyFactory(Schedulers.io())
    }
}
