package com.mindera.rocketscience.feature_demo.data.remote

import com.mindera.rocketscience.featue_demo.data.remote.services.SpaceXApiService


import com.mindera.rocketscience.core.NetworkResult
import com.mindera.rocketscience.featue_demo.data.remote.endpoint.SpaceXAPI
import com.mindera.rocketscience.featue_demo.domain.remote.AllLaunches
import com.mindera.rocketscience.featue_demo.domain.remote.CompanyInfo
import com.mindera.rocketscience.featue_demo.domain.remote.Fairings
import com.mindera.rocketscience.featue_demo.domain.remote.FirstStage
import com.mindera.rocketscience.featue_demo.domain.remote.Headquarters
import com.mindera.rocketscience.featue_demo.domain.remote.LaunchFailureDetails
import com.mindera.rocketscience.featue_demo.domain.remote.LaunchSite
import com.mindera.rocketscience.featue_demo.domain.remote.Links
import com.mindera.rocketscience.featue_demo.domain.remote.Rocket
import com.mindera.rocketscience.featue_demo.domain.remote.SecondStage
import com.mindera.rocketscience.featue_demo.domain.remote.Telemetry
import com.mindera.rocketscience.featue_demo.domain.remote.Timeline
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit

class SpaceXApiServiceTest {

    private lateinit var spaceXAPI: SpaceXAPI
    private lateinit var spaceXApiService: SpaceXApiService

    @Before
    fun setUp() {
        spaceXAPI = mockk()
        val retrofit: Retrofit = mockk()
        coEvery { retrofit.create(SpaceXAPI::class.java) } returns spaceXAPI
        spaceXApiService = SpaceXApiService(retrofit)
    }

    @Test
    fun `getCompanyInfo returns CompanyInfo on success`() = runBlocking {
        val mockResult = NetworkResult.Success(CompanyInfo(
            name = "Vanessa Sims",
            founder = "fabellas",
            founded = 8692,
            employees = 9595,
            vehicles = 7461,
            launchSites = 2662,
            testSites = 8212,
            ceo = "vero",
            cto = "gubergren",
            coo = "qui",
            ctoPropulsion = "utamur",
            valuation = 6919,
            headquarters = Headquarters(
                address = "graeci",
                city = "Americus",
                state = "Iowa"
            ),
            summary = "homero"

        ))
        coEvery { spaceXAPI.getCompanyInfo() } returns mockResult.data

        val result = spaceXApiService.getCompanyInfo()

        assertEquals(mockResult, result)
    }

    @Test
    fun `getAllLaunches returns list of AllLaunches on success`() = runBlocking {
        val mockResult = NetworkResult.Success(
            listOf(
                AllLaunches(
                    flightNumber = 9810,
                    missionName = "Beulah Benton",
                    missionId = listOf(),
                    upcoming = false,
                    launchYear = "invidunt",
                    launchDateUnix = 8836,
                    launchDateUtc = "2000-01-01T12:00:00.000Z",
                    launchDateLocal = "2020-12-13T12:30:00-05:00",
                    isTentative = false,
                    tentativeMaxPrecision = "viderer",
                    tbd = false,
                    launchWindow = 4004,
                    rocket = Rocket(
                        rocketId = "errem",
                        rocketName = "Jennie Cardenas",
                        rocketType = "solet",
                        firstStage = FirstStage(cores = listOf()),
                        secondStage = SecondStage(
                            block = 2055,
                            payloads = listOf()
                        ),
                        fairings = Fairings(
                            reused = false,
                            recoveryAttempt = false,
                            recovered = false,
                            ship = null
                        )
                    ),
                    ships = listOf(),
                    telemetry = Telemetry(flightClub = null),
                    launchSite = LaunchSite(
                        siteId = "inciderint",
                        siteName = "Jannie Miles",
                        siteNameLong = "Diana Flores"
                    ),
                    launchSuccess = false,
                    launchFailureDetails = LaunchFailureDetails(
                        time = 3490,
                        altitude = null,
                        reason = "lacus"
                    ),
                    links = Links(
                        missionPatch = "consectetur",
                        missionPatchSmall = "neque",
                        redditCampaign = null,
                        redditLaunch = null,
                        redditRecovery = null,
                        redditMedia = null,
                        presskit = null,
                        articleLink = "decore",
                        wikipedia = "mutat",
                        videoLink = "viderer",
                        youtubeId = "definitiones",
                        flickrImages = listOf()
                    ),
                    details = "hac",
                    staticFireDateUtc = "bibendum",
                    staticFireDateUnix = 2546,
                    timeline = Timeline(webcastLiftoff = 6996)
                )
            )
        )
        coEvery { spaceXAPI.getAllLaunches(any(), any(), any(), any(), any()) } returns mockResult.data

        val result = spaceXApiService.getAllLaunches("0", "2000-01-01", "2000-12-31", true)

        assertEquals(mockResult, result)
    }
}
