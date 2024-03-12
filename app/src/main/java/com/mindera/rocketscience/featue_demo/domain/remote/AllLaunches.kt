package com.mindera.rocketscience.featue_demo.domain.remote

import com.google.gson.annotations.SerializedName

data class AllLaunches(
    @SerializedName("flight_number") val flightNumber: Int,
    @SerializedName("mission_name") val missionName: String,
    @SerializedName("mission_id") val missionId: List<String>,
    @SerializedName("upcoming") val upcoming: Boolean,
    @SerializedName("launch_year") val launchYear: String,
    @SerializedName("launch_date_unix") val launchDateUnix: Long,
    @SerializedName("launch_date_utc") val launchDateUtc: String,
    @SerializedName("launch_date_local") val launchDateLocal: String,
    @SerializedName("is_tentative") val isTentative: Boolean,
    @SerializedName("tentative_max_precision") val tentativeMaxPrecision: String,
    @SerializedName("tbd") val tbd: Boolean,
    @SerializedName("launch_window") val launchWindow: Int,
    @SerializedName("rocket") val rocket: Rocket,
    @SerializedName("ships") val ships: List<String>,
    @SerializedName("telemetry") val telemetry: Telemetry,
    @SerializedName("launch_site") val launchSite: LaunchSite,
    @SerializedName("launch_success") val launchSuccess: Boolean,
    @SerializedName("launch_failure_details") val launchFailureDetails: LaunchFailureDetails,
    @SerializedName("links") val links: Links,
    @SerializedName("details") val details: String,
    @SerializedName("static_fire_date_utc") val staticFireDateUtc: String,
    @SerializedName("static_fire_date_unix") val staticFireDateUnix: Long,
    @SerializedName("timeline") val timeline: Timeline
)

data class Rocket(
    @SerializedName("rocket_id") val rocketId: String,
    @SerializedName("rocket_name") val rocketName: String,
    @SerializedName("rocket_type") val rocketType: String,
    @SerializedName("first_stage") val firstStage: FirstStage,
    @SerializedName("second_stage") val secondStage: SecondStage,
    @SerializedName("fairings") val fairings: Fairings
)

data class FirstStage(
    @SerializedName("cores") val cores: List<Core>
)

data class Core(
    @SerializedName("core_serial") val coreSerial: String,
    @SerializedName("flight") val flight: Int,
    @SerializedName("block") val block: Int?,
    @SerializedName("gridfins") val gridfins: Boolean,
    @SerializedName("legs") val legs: Boolean,
    @SerializedName("reused") val reused: Boolean,
    @SerializedName("land_success") val landSuccess: Boolean?,
    @SerializedName("landing_intent") val landingIntent: Boolean,
    @SerializedName("landing_type") val landingType: String?,
    @SerializedName("landing_vehicle") val landingVehicle: String?
)

data class SecondStage(
    @SerializedName("block") val block: Int,
    @SerializedName("payloads") val payloads: List<Payload>
)

data class Payload(
    @SerializedName("payload_id") val payloadId: String,
    @SerializedName("norad_id") val noradId: List<Int>,
    @SerializedName("reused") val reused: Boolean,
    @SerializedName("customers") val customers: List<String>,
    @SerializedName("nationality") val nationality: String,
    @SerializedName("manufacturer") val manufacturer: String,
    @SerializedName("payload_type") val payloadType: String,
    @SerializedName("payload_mass_kg") val payloadMassKg: Double,
    @SerializedName("payload_mass_lbs") val payloadMassLbs: Double,
    @SerializedName("orbit") val orbit: String,
    @SerializedName("orbit_params") val orbitParams: OrbitParams
)

data class OrbitParams(
    @SerializedName("reference_system") val referenceSystem: String,
    @SerializedName("regime") val regime: String,
    @SerializedName("longitude") val longitude: Double?,
    @SerializedName("semi_major_axis_km") val semiMajorAxisKm: Double?,
    @SerializedName("eccentricity") val eccentricity: Double?,
    @SerializedName("periapsis_km") val periapsisKm: Double,
    @SerializedName("apoapsis_km") val apoapsisKm: Double,
    @SerializedName("inclination_deg") val inclinationDeg: Double,
    @SerializedName("period_min") val periodMin: Double?,
    @SerializedName("lifespan_years") val lifespanYears: Double?,
    @SerializedName("epoch") val epoch: String?,
    @SerializedName("mean_motion") val meanMotion: Double?,
    @SerializedName("raan") val raan: Double?,
    @SerializedName("arg_of_pericenter") val argOfPericenter: Double?,
    @SerializedName("mean_anomaly") val meanAnomaly: Double?
)

data class Fairings(
    @SerializedName("reused") val reused: Boolean,
    @SerializedName("recovery_attempt") val recoveryAttempt: Boolean,
    @SerializedName("recovered") val recovered: Boolean,
    @SerializedName("ship") val ship: String?
)

data class Telemetry(
    @SerializedName("flight_club") val flightClub: String?
)

data class LaunchSite(
    @SerializedName("site_id") val siteId: String,
    @SerializedName("site_name") val siteName: String,
    @SerializedName("site_name_long") val siteNameLong: String
)

data class LaunchFailureDetails(
    @SerializedName("time") val time: Int,
    @SerializedName("altitude") val altitude: Int?,
    @SerializedName("reason") val reason: String
)

data class Links(
    @SerializedName("mission_patch") val missionPatch: String,
    @SerializedName("mission_patch_small") val missionPatchSmall: String,
    @SerializedName("reddit_campaign") val redditCampaign: String?,
    @SerializedName("reddit_launch") val redditLaunch: String?,
    @SerializedName("reddit_recovery") val redditRecovery: String?,
    @SerializedName("reddit_media") val redditMedia: String?,
    @SerializedName("presskit") val presskit: String?,
    @SerializedName("article_link") val articleLink: String,
    @SerializedName("wikipedia") val wikipedia: String,
    @SerializedName("video_link") val videoLink: String,
    @SerializedName("youtube_id") val youtubeId: String,
    @SerializedName("flickr_images") val flickrImages: List<String>
)

data class Timeline(
    @SerializedName("webcast_liftoff") val webcastLiftoff: Int
)
