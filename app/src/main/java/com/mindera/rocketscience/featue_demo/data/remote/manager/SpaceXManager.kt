package com.mindera.rocketscience.featue_demo.data.remote.manager

import com.mindera.rocketscience.core.NetworkResult
import com.mindera.rocketscience.featue_demo.data.remote.services.SpaceXApiService
import com.mindera.rocketscience.featue_demo.domain.remote.AllLaunches
import com.mindera.rocketscience.featue_demo.presentation.home.CompanyInfo
import com.mindera.rocketscience.featue_demo.presentation.home.Launch
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import javax.inject.Inject

class SpaceXManager @Inject constructor(
    private val spaceXAPIService: SpaceXApiService
) {
    suspend fun getCompanyInfo(): CompanyInfo? {
        return when (val companyInfo = spaceXAPIService.getCompanyInfo()) {
            is NetworkResult.Success -> {
                CompanyInfo(
                    companyName = companyInfo.data.name,
                    founderName = companyInfo.data.founder,
                    year = companyInfo.data.founded.toString(),
                    employees = companyInfo.data.employees.toString(),
                    launchSites = companyInfo.data.launchSites.toString(),
                    valuation = companyInfo.data.valuation.toString()
                )
            }

            else -> {
                /*todo deal with the error*/
                null
            }
        }
    }

    suspend fun getAllLaunches(
        offset: Int?,
        startDate: String?,
        endDate: String?,
        launchSuccess: Boolean?
    ): List<Launch>? {
        return when (val allLaunches = spaceXAPIService.getAllLaunches(
            offset = offset.toString(),
            startDate = startDate,
            endDate = endDate,
            launchSuccess = launchSuccess
        )){
            is NetworkResult.Success -> {
                val launchList: MutableList<Launch> = mutableListOf()
                allLaunches.data.forEach {
                    val instant = Instant.parse(it.launchDateUtc)
                    val date = instant.atZone(ZoneId.systemDefault()).toLocalDate()
                    val time = instant.atZone(ZoneId.systemDefault()).toLocalTime()
                    val currentDate = ZonedDateTime.now().toLocalDate()

                    val isInThePast = when {
                        date.isBefore(currentDate) -> true
                        else -> false
                    }
                    val daysBetween = ChronoUnit.DAYS.between(date, currentDate)

                    launchList.add(
                        Launch(
                            isLaunchInThePast = isInThePast,
                            missionName = it.missionName,
                            date = date.toString(),
                            time = time.toString(),
                            days = daysBetween.toString(),
                            missionPatch = it.links.missionPatchSmall,
                            rocketName = it.rocket.rocketName,
                            rocketType = it.rocket.rocketType,
                            successfulLaunch = it.launchSuccess,
                            articleUrl = it.links.articleLink,
                            wikiUrl = it.links.wikipedia,
                            videoUrl = it.links.videoLink
                        )
                    )
                }

                launchList
            }
            else -> {
                /*todo deal with the error*/
                null
            }
        }
    }
}