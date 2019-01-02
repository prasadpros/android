package com.sph.mobdatausage.features.home

import android.support.v7.widget.LinearLayoutManager
import com.sph.mobdatausage.R
import com.sph.mobdatausage.common.base.BaseActivity
import com.sph.mobdatausage.di.component.ActivityComponent
import com.sph.mobdatausage.exts.hide
import com.sph.mobdatausage.exts.setUpToolbar
import com.sph.mobdatausage.exts.show
import com.sph.mobdatausage.exts.showToast
import com.sph.mobdatausage.features.home.adapter.MobDataConsumptionAdapter
import com.sph.mobdatausage.model.DataConsumedYearly
import kotlinx.android.synthetic.main.activity_mobdata_stats.*
import javax.inject.Inject

class MobDataConsumptionActivity : BaseActivity<MobDataConsumptionPresenter>(),
        MobDataConsumptionAdapter.Listener,
        MobDataConsumptionView {


    @Inject
    lateinit var mobDataConsumptionAdapter: MobDataConsumptionAdapter

    override fun initView() {
        setUpToolbar(toolbar, resources.getString(R.string.statistics))
        setupRecyclerView()
        presenter.getMobileDataConsumption()
    }

    private fun setupRecyclerView() {
        mobDataConsumptionAdapter.setListener(this)
        rvNetworkStatistics.layoutManager = LinearLayoutManager(this)
        mobDataConsumptionAdapter.setListener(this)
        rvNetworkStatistics.adapter = mobDataConsumptionAdapter
    }

    override fun statisticsClicked(dataConsumedYearly: DataConsumedYearly) {
      if(dataConsumedYearly.isDataVolumeConsumptionDecreased){
          showToast("${resources.getString(R.string.data_volume_usage_decresed_msg)} " +
                  " in ${dataConsumedYearly.yearOfConsumption}")
      }
    }

    override fun showError() {
        showToast(resources.getString(R.string.error_msg))
    }

    override fun showLoader() {
        progressBar.show()
    }

    override fun hideLoader() {
        progressBar.hide()
    }

    override fun showNetworkUsgaeDetails(statistics: List<DataConsumedYearly>) {
        mobDataConsumptionAdapter.updateArticles(statistics)
    }

    override val layout: Int = R.layout.activity_mobdata_stats

    override fun inject(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    override fun attachView() {
        presenter.attachView(this)
    }
}
