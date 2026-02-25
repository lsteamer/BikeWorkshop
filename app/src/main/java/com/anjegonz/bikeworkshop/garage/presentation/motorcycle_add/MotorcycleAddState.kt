package com.anjegonz.bikeworkshop.garage.presentation.motorcycle_add

import com.anjegonz.bikeworkshop.garage.domain.MotorcycleType

data class MotorcycleAddState(

    //validate each of the data, given time
    val manufacturerText: String = "",
    val modelText: String = "",
    val powerPS: String = "",
    val motorcycleType: MotorcycleType = MotorcycleType.CRUISER,
    val yearOfConstructionValue: String = "",

    //validate this proper
    val areAllFieldsFilled: Boolean = true
)