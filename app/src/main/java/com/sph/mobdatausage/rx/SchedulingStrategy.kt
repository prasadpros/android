package com.sph.mobdatausage.rx

import io.reactivex.*

class SchedulingStrategy<T>(private val subscribingScheduler: Scheduler,
                            private val observingScheduler: Scheduler)
    : SingleTransformer<T, T>, ObservableTransformer<T, T> {

    override fun apply(upstream: Observable<T>): ObservableSource<T> {
        return upstream
                .subscribeOn(subscribingScheduler)
                .observeOn(observingScheduler)
    }

    override fun apply(upstream: Single<T>): SingleSource<T> {
        return upstream
                .subscribeOn(subscribingScheduler)
                .observeOn(observingScheduler)
    }

    open class Factory(private val subscribingScheduler: Scheduler, private val observingScheduler: Scheduler) {

        fun <T> create(): SchedulingStrategy<T> {
            return SchedulingStrategy(subscribingScheduler, observingScheduler)
        }
    }
}
