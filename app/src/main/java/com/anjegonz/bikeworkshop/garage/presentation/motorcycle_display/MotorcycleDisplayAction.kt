package com.anjegonz.bikeworkshop.garage.presentation.motorcycle_display

import com.anjegonz.bikeworkshop.garage.domain.Motorcycle

sealed interface MotorcycleDisplayAction {
    data class onMotorcycleDeleteClick(val motorcycle: Motorcycle) : MotorcycleDisplayAction
    data class onMotorcycleClick(val motorcycle: Motorcycle) : MotorcycleDisplayAction
}