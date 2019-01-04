package com.sph.mobdatausage.common.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.sph.MobDataConsumptionApp
import com.sph.mobdatausage.R
import com.sph.mobdatausage.common.mvp.BasePresenter
import com.sph.mobdatausage.common.mvp.BaseView
import com.sph.mobdatausage.di.component.ActivityComponent
import com.sph.mobdatausage.di.component.DaggerActivityComponent
import com.sph.mobdatausage.di.module.AppModule
import com.sph.mobdatausage.exts.hasNetwork
import com.sph.mobdatausage.exts.showToast
import javax.inject.Inject

abstract class BaseActivity<P : BasePresenter<out BaseView>> : AppCompatActivity() {

    @Inject
    lateinit var presenter: P


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout)
        inject(setupActivityComponent())
        attachView()
        initView()
        checkNetwork()
    }

    protected abstract fun inject(activityComponent: ActivityComponent)

    private fun setupActivityComponent(): ActivityComponent {
        return DaggerActivityComponent.builder()
                .appComponent(MobDataConsumptionApp.instance.component)
                .appModule(AppModule(application))
                .build()
    }

    protected abstract fun attachView()

    protected abstract fun initView()

    protected abstract val layout: Int

    private fun checkNetwork() {
        if (!hasNetwork(this)) {
            showToast(resources.getString(R.string.network_issue_msg))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }


}
