package com.sph.mobdatausage.features.home

import com.sph.mobdatausage.common.mvp.BaseView
import com.sph.mobdatausage.model.DataConsumedYearly

interface MobDataConsumptionView : BaseView {
    fun showError()
    fun showLoader()
    fun hideLoader()
    fun showNetworkUsgaeDetails(statistics: List<DataConsumedYearly>)

}