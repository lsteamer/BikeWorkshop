package com.anjegonz.bikeworkshop.garage.presentation.motorcycle_list


import com.anjegonz.bikeworkshop.garage.domain.Motorcycle

sealed interface MotorcycleListAction {

    data class OnMotorcycleClick(val motorcycle: Motorcycle) : MotorcycleListAction

}