package com.sph.mobdatausage.features

import com.news.carousell.rx.TestSchedulingStrategyFactory
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import com.sph.mobdatausage.common.LIMIT
import com.sph.mobdatausage.common.OFFSET
import com.sph.mobdatausage.common.RESOURCE_ID
import com.sph.mobdatausage.features.home.MobDataConsumptionPresenter
import com.sph.mobdatausage.features.home.MobDataConsumptionUseCase
import com.sph.mobdatausage.features.home.MobDataConsumptionView
import com.sph.mobdatausage.mockdata.getMobileConsumptionData
import com.sph.mobdatausage.mockdata.getRecordsWithRisingQuarterlyConsumption
import com.sph.mobdatausage.network.MobDataConsumptionApi
import io.reactivex.Single
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.net.UnknownHostException


@RunWith(MockitoJUnitRunner::class)
class MobDataConsumptionPresenterTest {

    private lateinit var presenter: MobDataConsumptionPresenter
    private val networkConsumptionApi: MobDataConsumptionApi = mock()
    private val view: MobDataConsumptionView = mock()

    @Before
    fun setUp() {
        presenter = MobDataConsumptionPresenter(networkConsumptionApi, TestSchedulingStrategyFactory.immediate())
        presenter.attachView(view)
        whenever(networkConsumptionApi.getNetConsumptionDetails(RESOURCE_ID, LIMIT, OFFSET))
                .thenReturn(Single.just(getMobileConsumptionData()))
    }

    @Test
    fun `verify interaction while fecthing the data`() {
        presenter.getMobileDataConsumption()
        view.showLoader()
        view.showNetworkUsgaeDetails(MobDataConsumptionUseCase()
                .buildDataConsumedYearly(getRecordsWithRisingQuarterlyConsumption()))
        view.hideLoader()
    }

    @Test
    fun `verify the data when fetching success`() {
        presenter.getMobileDataConsumption()
        val testObserver = networkConsumptionApi.getNetConsumptionDetails(RESOURCE_ID, LIMIT, OFFSET).test()
        testObserver.assertSubscribed()
        testObserver.assertValues(getMobileConsumptionData())
        testObserver.assertComplete()
    }

    @Test
    fun `verify exception is thrown when fetching fails`() {
        val exception = UnknownHostException("failed")
        whenever(networkConsumptionApi.getNetConsumptionDetails(RESOURCE_ID, LIMIT, OFFSET))
                .thenReturn(Single.error(exception))
        presenter.getMobileDataConsumption()
        val testObserver = networkConsumptionApi.getNetConsumptionDetails(RESOURCE_ID, LIMIT, OFFSET)
                .test()
        testObserver.assertError(exception)
    }

    @After
    fun tearDown() {
        presenter.detachView()
    }
}