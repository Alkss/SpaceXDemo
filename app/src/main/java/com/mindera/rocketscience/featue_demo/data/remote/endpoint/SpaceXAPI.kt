package com.mindera.rocketscience.featue_demo.data.remote.endpoint

import com.mindera.rocketscience.featue_demo.domain.remote.AllLaunches
import com.mindera.rocketscience.featue_demo.domain.remote.CompanyInfo
import retrofit2.http.GET
import retrofit2.http.Query

interface SpaceXAPI {
    @GET("v3/info")
    suspend fun getCompanyInfo(): CompanyInfo

    @GET("v3/launches")
    suspend fun getAllLaunches(
        @Query("limit") limit: String = "20",
        @Query("offset") offset: String? = null,
        @Query("start") start: String? = null,
        @Query("end") end: String? = null,
        @Query("launch_success") launchSuccess: Boolean? = null
    ): List<AllLaunches>
}