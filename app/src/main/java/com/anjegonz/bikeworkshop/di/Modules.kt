package com.anjegonz.bikeworkshop.di

import androidx.room.Room
import com.anjegonz.bikeworkshop.garage.data.database.MotorcycleDatabase
import com.anjegonz.bikeworkshop.garage.data.repository.MotorcycleRepositoryImpl
import com.anjegonz.bikeworkshop.garage.domain.MotorcycleRepository
import com.anjegonz.bikeworkshop.garage.presentation.motorcycle_add.MotorcycleAddViewModel
import com.anjegonz.bikeworkshop.garage.presentation.motorcycle_display.MotorcycleDisplayViewModel
import com.anjegonz.bikeworkshop.garage.presentation.motorcycle_list.MotorcycleListViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

var koinModule = module {

    // Database
    single {
        Room.databaseBuilder(
            context = get(),
            klass = MotorcycleDatabase::class.java,
            name = "motorcycle_database"
        ).build()
    }

    // DAO
    single { get<MotorcycleDatabase>().motorcycleDao }

    // Repository
    singleOf(::MotorcycleRepositoryImpl).bind<MotorcycleRepository>()

    // ViewModels
    viewModelOf(::MotorcycleListViewModel)
    viewModelOf(::MotorcycleDisplayViewModel)
    viewModelOf(::MotorcycleAddViewModel)

}