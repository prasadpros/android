package com.sph.mobdatausage.model

data class DataConsumedYearly(
        var yearOfConsumption: String,
        var totalVolumeConsumed: Float,
        var isDataVolumeConsumptionDecreased: Boolean,
        var quarterlyData: List<String>)