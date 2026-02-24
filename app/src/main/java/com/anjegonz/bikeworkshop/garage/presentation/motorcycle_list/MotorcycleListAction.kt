package com.anjegonz.bikeworkshop.garage.presentation.motorcycle_list


import com.anjegonz.bikeworkshop.garage.presentation.core.MotorcycleUIModel

sealed interface MotorcycleListAction {

    data class OnMotorcycleClick(val motorcycle: MotorcycleUIModel) : MotorcycleListAction

}