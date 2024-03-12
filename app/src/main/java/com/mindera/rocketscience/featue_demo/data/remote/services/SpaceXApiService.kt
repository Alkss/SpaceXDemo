package com.mindera.rocketscience.featue_demo.data.remote.services

import com.mindera.rocketscience.core.NetworkResult
import com.mindera.rocketscience.featue_demo.data.remote.endpoint.SpaceXAPI
import com.mindera.rocketscience.featue_demo.domain.remote.AllLaunches
import com.mindera.rocketscience.featue_demo.domain.remote.CompanyInfo
import retrofit2.Retrofit
import javax.inject.Inject

class SpaceXApiService @Inject constructor(
    retrofit: Retrofit
) {
    private val api: SpaceXAPI = retrofit.create(SpaceXAPI::class.java)

    suspend fun getCompanyInfo(): NetworkResult<CompanyInfo> {
        try {
            val companyInfo = api.getCompanyInfo()
            return NetworkResult.Success(companyInfo)
        } catch (e: Exception) {
            return NetworkResult.Error(e)
        }
    }

    suspend fun getAllLaunches(
        offset: String?,
        startDate: String?,
        endDate: String?,
        launchSuccess: Boolean?
    ): NetworkResult<List<AllLaunches>> {
        try {
            val allLaunches = api.getAllLaunches(
                offset = offset,
                start = startDate,
                end = endDate,
                launchSuccess = launchSuccess
            )
            return NetworkResult.Success(allLaunches)
        } catch (e: Exception) {
            return NetworkResult.Error(e)
        }
    }
}