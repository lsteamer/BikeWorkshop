package com.anjegonz.bikeworkshop.garage.presentation.motorcycle_list

import com.anjegonz.bikeworkshop.garage.domain.MotorcycleType
import com.anjegonz.bikeworkshop.garage.domain.MotorcycleUIModel

data class MotorcycleListState(
    val isLoading: Boolean = true,
    val motorcycles: List<MotorcycleUIModel> = emptyList(),
)

val sometest = MotorcycleListState(
    isLoading = false,
    motorcycles = listOf(
        MotorcycleUIModel(
            id = 1,
            manufacturer = "Ducati",
            model = "Panigale V4",
            powerPS = 208,
            type = MotorcycleType.SPORT,
            yearOfConstruction = 2021,
            age = 4
        ),
        MotorcycleUIModel(
            id = 2,
            manufacturer = "BMW",
            model = "R 1250 GS",
            powerPS = 136,
            type = MotorcycleType.ADVENTURE,
            yearOfConstruction = 2020,
            age = 5
        ),
        MotorcycleUIModel(
            id = 3,
            manufacturer = "Harley-Davidson",
            model = "Softail Standard",
            powerPS = 95,
            type = MotorcycleType.CRUISER,
            yearOfConstruction = 2019,
            age = 6
        )
    )
)