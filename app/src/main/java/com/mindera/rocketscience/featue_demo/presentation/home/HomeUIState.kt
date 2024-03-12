package com.mindera.rocketscience.featue_demo.presentation.home

data class HomeUIState(
    val companyInfo: CompanyInfo,
    val launchList: List<Launch>
)

data class Launch(
    val isLaunchInThePast: Boolean = false,
    val missionName: String = "",
    val date: String = "",
    val time: String = "",
    val days: String = "",
    val missionPatch: String? = null,
    val rocketName: String = "",
    val rocketType: String = "",
    val successfulLaunch: Boolean = false,
    val articleUrl: String? = null,
    val wikiUrl: String? = null,
    val videoUrl: String? = null
)

data class CompanyInfo(
    val companyName: String = "",
    val founderName: String = "",
    val year: String = "",
    val employees: String = "",
    val launchSites: String = "",
    val valuation: String = ""
)