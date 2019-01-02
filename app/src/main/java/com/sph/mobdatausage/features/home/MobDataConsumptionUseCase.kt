package com.sph.mobdatausage.features.home;

import com.sph.mobdatausage.model.DataConsumedYearly
import com.sph.mobdatausage.model.Record

class MobDataConsumptionUseCase {


    fun buildDataConsumedYearly(records: List<Record>):
            List<DataConsumedYearly> {
        var results: List<DataConsumedYearly> = mutableListOf()
        var quarterlyData: List<String> = mutableListOf()
        var year = records.get(0).quarter.split("-")[0]
        var totalConsumption = 0f
        var currentVolume = records.get(0).volume_of_mobile_data.toFloat()
        var isDataVolumeConsumptionDecreased = false
        for (record in records) {
            if (record.quarter.contains(year)) {
                var volume = record.volume_of_mobile_data.toFloat()
                totalConsumption += volume
                if (!isDataVolumeConsumptionDecreased) {
                    if (currentVolume <= volume)
                        currentVolume = volume
                    else
                        isDataVolumeConsumptionDecreased = true
                }
                quarterlyData += record.volume_of_mobile_data

            } else {

                results += DataConsumedYearly(year, totalConsumption,
                        isDataVolumeConsumptionDecreased, quarterlyData)
                totalConsumption = record.volume_of_mobile_data.toFloat()
                isDataVolumeConsumptionDecreased = false
                year = record.quarter.split("-")[0]
                quarterlyData = emptyList()
                quarterlyData += record.volume_of_mobile_data
                currentVolume = record.volume_of_mobile_data.toFloat()
            }
        }
        if (year.toInt() == 2018) {
            quarterlyData += "N/A"
            quarterlyData += "N/A"
            results += DataConsumedYearly(year, totalConsumption,
                    isDataVolumeConsumptionDecreased, quarterlyData)
        }

        return results
    }
}
