package com.anjegonz.bikeworkshop.di

import com.anjegonz.bikeworkshop.garage.presentation.motorcycle_add.MotorcycleAddViewModel
import com.anjegonz.bikeworkshop.garage.presentation.motorcycle_display.MotorcycleDisplayViewModel
import com.anjegonz.bikeworkshop.garage.presentation.motorcycle_list.MotorcycleListViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

var koinModule = module {
    viewModelOf(::MotorcycleListViewModel)
    viewModelOf(::MotorcycleDisplayViewModel)
    viewModelOf(::MotorcycleAddViewModel)
}