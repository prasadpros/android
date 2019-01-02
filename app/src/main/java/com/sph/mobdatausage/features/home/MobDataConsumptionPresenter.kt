package com.sph.mobdatausage.features.home

import com.sph.mobdatausage.common.LIMIT
import com.sph.mobdatausage.common.OFFSET
import com.sph.mobdatausage.common.RESOURCE_ID
import com.sph.mobdatausage.common.mvp.BasePresenter
import com.sph.mobdatausage.network.MobDataConsumptionApi
import com.sph.mobdatausage.rx.SchedulingStrategy
import io.reactivex.rxkotlin.plusAssign
import timber.log.Timber
import javax.inject.Inject

class MobDataConsumptionPresenter @Inject constructor(private val mobDataConsumptionApi: MobDataConsumptionApi,
                                                      private val schedulers: SchedulingStrategy.Factory)
    : BasePresenter<MobDataConsumptionView>() {

    fun getMobileDataConsumption() {
        view?.showLoader()
        compositeDisposable += mobDataConsumptionApi.getNetConsumptionDetails(
                RESOURCE_ID, LIMIT, OFFSET)
                .compose(schedulers.create())
                .map {
                    MobDataConsumptionUseCase().buildDataConsumedYearly(it.result.records)
                }
                .subscribe({
                    view?.hideLoader()
                    view?.showNetworkUsgaeDetails(it)

                }, {
                    Timber.e(it)
                    view?.hideLoader()
                })
    }

}
