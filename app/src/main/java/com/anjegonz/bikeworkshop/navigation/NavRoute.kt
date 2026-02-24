package com.anjegonz.bikeworkshop.navigation

import kotlinx.serialization.Serializable

sealed interface NavRoute {
    @Serializable
    data object MotorcycleList : NavRoute

    @Serializable
    data object MotorcycleAdd : NavRoute

    @Serializable
    data class MotorcycleDisplay(val id: Int) : NavRoute
}