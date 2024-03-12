package com.mindera.rocketscience.featue_demo.presentation.home

sealed class HomeEvent {
    data object NextPageRequest: HomeEvent()
    data object FilterPage: HomeEvent()
}
