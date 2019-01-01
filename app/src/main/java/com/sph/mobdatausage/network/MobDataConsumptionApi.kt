package com.sph.mobdatausage.network

import com.sph.mobdatausage.model.MobDataConsumption
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MobDataConsumptionApi {

    @GET("datastore_search")
    fun getNetConsumptionDetails(@Query("resource_id")
                                 resourceId: String,
                                 @Query("limit") limit: Int,
                                 @Query("offset") offset: Int): Single<MobDataConsumption>
}