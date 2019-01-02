package com.sph.mobdatausage.rx

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers

class AndroidSchedulingStrategyFactory(subscribingScheduler: Scheduler) :
        SchedulingStrategy.Factory(subscribingScheduler, AndroidSchedulers.mainThread())
