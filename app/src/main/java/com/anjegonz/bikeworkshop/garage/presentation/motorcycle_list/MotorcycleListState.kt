package com.anjegonz.bikeworkshop.garage.presentation.motorcycle_list

import com.anjegonz.bikeworkshop.garage.domain.Motorcycle

data class MotorcycleListState(
    //
    val isLoading: Boolean = false,
    val motorcycles: List<Motorcycle> = emptyList(),
)
