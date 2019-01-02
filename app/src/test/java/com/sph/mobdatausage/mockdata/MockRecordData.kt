package com.sph.mobdatausage.mockdata

import com.sph.mobdatausage.model.Field
import com.sph.mobdatausage.model.Links
import com.sph.mobdatausage.model.MobDataConsumption
import com.sph.mobdatausage.model.Record

fun getRecordsWithRisingQuarterlyConsumption(): MutableList<Record> {
    // 2016 - 57.58268 , 2017 - 65.18268 , 2018 - 75.38268
    val records = mutableListOf<Record>()
    records += Record(1, "2016-Q1", "13.34567")
    records += Record(2, "2016-Q2", "14.34567")
    records += Record(3, "2016-Q3", "14.54567")
    records += Record(4, "2016-Q4", "15.34567")
    records += Record(5, "2017-Q1", "15.84567")
    records += Record(5, "2017-Q2", "16.04567")
    records += Record(7, "2017-Q3", "16.34567")
    records += Record(8, "2017-Q4", "16.94567")
    records += Record(9, "2018-Q1", "17.34567")
    records += Record(10, "2018-Q2", "18.34567")
    records += Record(11, "2018-Q3", "19.34567")
    records += Record(12, "2018-Q4", "20.34567")

    return records
}

fun getRecordsWithPartialData(): MutableList<Record> {
    // 2016 - 57.58268 , 2017 - 61.88268 , 2018 - 35.69134
    val records = mutableListOf<Record>()
    records += Record(1, "2016-Q1", "13.34567")
    records += Record(2, "2016-Q2", "14.34567")
    records += Record(3, "2016-Q3", "14.54567")
    records += Record(4, "2016-Q4", "15.34567")
    records += Record(5, "2017-Q1", "15.84567")
    records += Record(6, "2017-Q2", "14.34567")
    records += Record(7, "2017-Q3", "15.34567")
    records += Record(8, "2017-Q4", "16.34567")
    records += Record(9, "2018-Q1", "17.34567")
    records += Record(10, "2018-Q2", "18.34567")

    return records
}

fun getRecordsWithFallingQuarterlyConsumption(): MutableList<Record> {
    val records = mutableListOf<Record>()
    records += Record(1, "2016-Q1", "13.34567")
    records += Record(2, "2016-Q2", "14.34567")
    records += Record(3, "2016-Q3", "14.54567")
    records += Record(4, "2016-Q4", "15.34567")
    records += Record(5, "2017-Q1", "15.84567")
    records += Record(6, "2017-Q2", "14.34567")
    records += Record(7, "2017-Q3", "15.34567")
    records += Record(8, "2017-Q4", "16.34567")
    records += Record(9, "2018-Q1", "17.34567")
    records += Record(10, "2018-Q2", "18.34567")
    records += Record(11, "2018-Q3", "19.34567")
    records += Record(12, "2018-Q4", "20.34567")

    return records

}

fun getMobileConsumptionData(): MobDataConsumption {
    return MobDataConsumption("help", com.sph.mobdatausage.model.Result(Links("start", "next"),
            emptyList<Field>(), 56, getRecordsWithRisingQuarterlyConsumption(), "", 56), true)
}