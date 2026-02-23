package com.anjegonz.bikeworkshop.garage.presentation.motorcycle_list


import com.anjegonz.bikeworkshop.garage.domain.MotorcycleUIModel

sealed interface MotorcycleListAction {

    data class OnMotorcycleClick(val motorcycle: MotorcycleUIModel) : MotorcycleListAction
    data object OnNewMotorcycleClicked : MotorcycleListAction

}