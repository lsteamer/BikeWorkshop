package com.anjegonz.bikeworkshop.garage.presentation.motorcycle_display

import com.anjegonz.bikeworkshop.garage.presentation.core.MotorcycleUIModel

sealed interface MotorcycleDisplayAction {
    data class onMotorcycleDeleteClick(val motorcycle: MotorcycleUIModel) : MotorcycleDisplayAction
    data class onMotorcycleClick(val motorcycle: MotorcycleUIModel) : MotorcycleDisplayAction
}