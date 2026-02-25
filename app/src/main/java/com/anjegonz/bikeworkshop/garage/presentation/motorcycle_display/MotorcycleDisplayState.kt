package com.anjegonz.bikeworkshop.garage.presentation.motorcycle_display

import com.anjegonz.bikeworkshop.garage.domain.Motorcycle

data class MotorcycleDisplayState(
    val isLoading: Boolean = true,
    val mainMotorcycle: Motorcycle? = null,
    val ageOfMotorcycle: Int = 0,
    val motorcycleCarousel: List<Motorcycle> = emptyList()
)