package com.sph.mobdatausage.di.module

import com.sph.mobdatausage.network.MobDataConsumptionApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module(includes = [NetworkModule::class])
class ApiModule {

    @Provides
    internal fun providesNetworkConsumptionApii(retrofit: Retrofit): MobDataConsumptionApi {
        return retrofit.create(MobDataConsumptionApi::class.java)
    }
}
