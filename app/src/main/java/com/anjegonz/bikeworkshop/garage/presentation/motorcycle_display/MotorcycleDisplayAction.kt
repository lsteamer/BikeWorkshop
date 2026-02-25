package com.anjegonz.bikeworkshop.garage.presentation.motorcycle_display

import com.anjegonz.bikeworkshop.garage.domain.Motorcycle

sealed interface MotorcycleDisplayAction {
    data object onMotorcycleDeleteClick : MotorcycleDisplayAction
    data class onMotorcycleClick(val motorcycle: Motorcycle) : MotorcycleDisplayAction
}