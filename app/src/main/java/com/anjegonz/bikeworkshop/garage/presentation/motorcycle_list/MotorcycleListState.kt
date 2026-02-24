package com.anjegonz.bikeworkshop.garage.presentation.motorcycle_list

import com.anjegonz.bikeworkshop.garage.domain.MotorcycleType
import com.anjegonz.bikeworkshop.garage.domain.MotorcycleUIModel

data class MotorcycleListState(
    val isLoading: Boolean = true,
    val motorcycles: List<MotorcycleUIModel> = emptyList(),
)
