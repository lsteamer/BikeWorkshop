package com.anjegonz.bikeworkshop.garage.presentation.motorcycle_display

import com.anjegonz.bikeworkshop.garage.presentation.core.MotorcycleUIModel

data class MotorcycleDisplayState(
    val isLoading: Boolean = true,
    val mainMotorcycle:  MotorcycleUIModel? = null,
    val motorcycleCarousel: List<MotorcycleUIModel> = emptyList()
)