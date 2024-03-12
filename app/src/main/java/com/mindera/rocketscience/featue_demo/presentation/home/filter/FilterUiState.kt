package com.mindera.rocketscience.featue_demo.presentation.home.filter

data class FilterUiState(
    val offset: Int? = 0,
    val startDate: String? = null,
    val endDate: String? = null,
    val isSuccessful: Boolean? = null,
    val orderAscending: Boolean = true
)
