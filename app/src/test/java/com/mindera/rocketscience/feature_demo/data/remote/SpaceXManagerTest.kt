package com.mindera.rocketscience.feature_demo.data.remote


import com.mindera.rocketscience.core.NetworkResult
import com.mindera.rocketscience.featue_demo.data.remote.manager.SpaceXManager
import com.mindera.rocketscience.featue_demo.data.remote.services.SpaceXApiService
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
import com.mindera.rocketscience.featue_demo.presentation.home.Launch
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.time.format.DateTimeFormatter

class SpaceXManagerTest {

    private lateinit var spaceXApiService: SpaceXApiService
    private lateinit var spaceXManager: SpaceXManager

    @Before
    fun setUp() {
        spaceXApiService = mockk()
        spaceXManager = SpaceXManager(spaceXApiService)
    }

    @Test
    fun `getCompanyInfo returns CompanyInfo on success`() = runBlocking {
        val mockResult = NetworkResult.Success(
            CompanyInfo(
                name = "Crystal Hogan",
                founder = "contentiones",
                founded = 4025,
                employees = 8428,
                vehicles = 9138,
                launchSites = 4773,
                testSites = 5338,
                ceo = "aeque",
                cto = "sed",
                coo = "numquam",
                ctoPropulsion = "facilisis",
                valuation = 1200,
                headquarters = Headquarters(
                    address = "quam",
                    city = "Bon Accord",
                    state = "Kentucky"
                ),
                summary = "theophrastus"
            )
        )
        coEvery { spaceXApiService.getCompanyInfo() } returns mockResult

        val result = spaceXManager.getCompanyInfo()

        assertEquals(mockResult.data.name, result?.companyName)
        assertEquals(mockResult.data.founder, result?.founderName)
        assertEquals(mockResult.data.founded.toString(), result?.year)
        assertEquals(mockResult.data.employees.toString(), result?.employees)
        assertEquals(mockResult.data.launchSites.toString(), result?.launchSites)
        assertEquals(mockResult.data.valuation.toString(), result?.valuation)
    }


    @Test
    fun `getAllLaunches returns list of Launch on success`(): Unit = runBlocking {
        val mockLaunch = AllLaunches(
            flightNumber = 1823,
            missionName = "Enrique Underwood",
            missionId = listOf(),
            upcoming = false,
            launchYear = "regione",
            launchDateUnix = 6027,
            launchDateUtc = "2000-01-01T12:00:00.000Z",
            launchDateLocal = "2020-12-13T12:30:00-05:00",
            isTentative = false,
            tentativeMaxPrecision = "rhoncus",
            tbd = false,
            launchWindow = 5126,
            rocket = Rocket(
                rocketId = "quis",
                rocketName = "Amber Branch",
                rocketType = "fugit",
                firstStage = FirstStage(cores = listOf()),
                secondStage = SecondStage(
                    block = 1436,
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
                siteId = "atqui",
                siteName = "Sharlene Dillon",
                siteNameLong = "Heriberto Noel"
            ),
            launchSuccess = false,
            launchFailureDetails = LaunchFailureDetails(
                time = 4057,
                altitude = null,
                reason = "eget"
            ),
            links = Links(
                missionPatch = "intellegat",
                missionPatchSmall = "debet",
                redditCampaign = null,
                redditLaunch = null,
                redditRecovery = null,
                redditMedia = null,
                presskit = null,
                articleLink = "quidam",
                wikipedia = "parturient",
                videoLink = "ferri",
                youtubeId = "meliore",
                flickrImages = listOf()
            ),
            details = "convallis",
            staticFireDateUtc = "numquam",
            staticFireDateUnix = 2812,
            timeline = Timeline(webcastLiftoff = 2482)
        )
        val mockResult = NetworkResult.Success(listOf(mockLaunch))
        coEvery { spaceXApiService.getAllLaunches(any(), any(), any(), any()) } returns mockResult

        val result = spaceXManager.getAllLaunches(0, "2000-01-01", "2000-12-31", true)

        assertEquals(1, result?.size)
        result?.let {
            assertEquals(mockLaunch.missionName, it[0].missionName)
            assertEquals(mockLaunch.launchSuccess, it[0].successfulLaunch)
            assertEquals(mockLaunch.links.missionPatchSmall, it[0].missionPatch)
            assertEquals(mockLaunch.links.articleLink, it[0].articleUrl)
            assertEquals(mockLaunch.links.wikipedia, it[0].wikiUrl)
            assertEquals(mockLaunch.links.videoLink, it[0].videoUrl)
            assertEquals(mockLaunch.rocket.rocketName, it[0].rocketName)
            assertEquals(mockLaunch.rocket.rocketType, it[0].rocketType)
        }
    }

}

