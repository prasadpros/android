package com.sph.mobdatausage.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class AppModule(val application: Application) {

    @Provides
    internal fun provideContext(): Context {
        return application
    }
}
