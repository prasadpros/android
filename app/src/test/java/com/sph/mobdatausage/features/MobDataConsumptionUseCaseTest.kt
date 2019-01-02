package com.sph.mobdatausage.features

import com.sph.mobdatausage.features.home.MobDataConsumptionUseCase
import com.sph.mobdatausage.mockdata.getRecordsWithFallingQuarterlyConsumption
import com.sph.mobdatausage.mockdata.getRecordsWithPartialData
import com.sph.mobdatausage.mockdata.getRecordsWithRisingQuarterlyConsumption
import org.junit.Before
import org.junit.Test


class MobDataConsumptionUseCaseTest {

    private lateinit var mobDataConsumptionUseCase: MobDataConsumptionUseCase

    @Before
    fun setup() {
        mobDataConsumptionUseCase = MobDataConsumptionUseCase()
    }

    @Test
    fun `should check raising quarterly data consumption`() {
        val recordData = getRecordsWithRisingQuarterlyConsumption()
        val yearlyConsumptionData = mobDataConsumptionUseCase.buildDataConsumedYearly(recordData)
        assert(yearlyConsumptionData.size == recordData.size / 4)
        for (data in yearlyConsumptionData) {
            assert(!data.isDataVolumeConsumptionDecreased)
        }
    }

    @Test
    fun `should check falling quarterly data consumption`() {
        val recordData = getRecordsWithFallingQuarterlyConsumption()
        val yearlyConsumptionData = mobDataConsumptionUseCase.buildDataConsumedYearly(recordData)
        assert(yearlyConsumptionData.size == recordData.size / 4)
        for (data in yearlyConsumptionData) {
            if (data.yearOfConsumption.equals("2017"))
                assert(data.isDataVolumeConsumptionDecreased)
            else
                assert(!data.isDataVolumeConsumptionDecreased)
        }
    }

    @Test
    fun `should check partial quarterly  data consumption`() {
        val recordData = getRecordsWithPartialData()
        val yearlyConsumptionData = mobDataConsumptionUseCase.buildDataConsumedYearly(recordData)

        for (data in yearlyConsumptionData) {
            if (data.yearOfConsumption.equals("2018")) {
                assert(data.quarterlyData[2].equals("N/A"))
                assert(data.quarterlyData[3].equals("N/A"))
            }
        }
        assert(yearlyConsumptionData.size == (recordData.size / 4) + 1)
    }

    @Test
    fun `should check total yearly  data consumption`() {
        val recordData = getRecordsWithRisingQuarterlyConsumption()
        val yearlyConsumptionData = mobDataConsumptionUseCase.buildDataConsumedYearly(recordData)
        val expectedDataList = listOf(57.58268f, 65.18268f, 75.38268f)
        for (i in 0 until yearlyConsumptionData.size) {
            assert("%.5f".format(yearlyConsumptionData[i].totalVolumeConsumed).toFloat()
                    == expectedDataList[i])
        }
    }

    @Test
    fun `should check total yearly   partial data consumption`() {
        val recordData = getRecordsWithPartialData()
        val yearlyConsumptionData = mobDataConsumptionUseCase.buildDataConsumedYearly(recordData)
        val expectedDataList = listOf(57.58268f, 61.88268f, 35.69134f)
        for (i in 0 until yearlyConsumptionData.size) {
            assert("%.5f".format(yearlyConsumptionData[i].totalVolumeConsumed).toFloat()
                    == expectedDataList[i])
        }
    }
}