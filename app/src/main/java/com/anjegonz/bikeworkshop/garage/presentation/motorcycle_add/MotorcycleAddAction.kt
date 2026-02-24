package com.anjegonz.bikeworkshop.garage.presentation.motorcycle_add

import com.anjegonz.bikeworkshop.garage.domain.MotorcycleType

sealed interface MotorcycleAddAction {

    data class OnManufacturerTextFieldChange(val manufacturerText: String) : MotorcycleAddAction
    data class OnModelTextFieldChange(val modelText: String) : MotorcycleAddAction
    data class OnPowerPSTextFieldChange(val powerPSText: String) : MotorcycleAddAction
    data class OnTypeDropdownMenuChange(val typeValue: MotorcycleType) : MotorcycleAddAction
    data class OnYearTextFieldChange(val yearText: String) : MotorcycleAddAction

    data object OnClickOnSubmit : MotorcycleAddAction

}