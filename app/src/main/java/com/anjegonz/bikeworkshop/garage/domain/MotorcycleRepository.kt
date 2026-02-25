package com.anjegonz.bikeworkshop.garage.domain

import com.anjegonz.bikeworkshop.garage.domain.core.DataError
import com.anjegonz.bikeworkshop.garage.domain.core.Result
import kotlinx.coroutines.flow.Flow

interface MotorcycleRepository {

    fun getMotorcycles(): Flow<List<Motorcycle>>

    fun getMotorcyclesByManufacturer(manufacturer: String): Flow<List<Motorcycle>>

    suspend fun insertMotorcycle(motorcycle: Motorcycle): Result<Unit, DataError.Local>

    suspend fun deleteMotorcycle(motorcycle: Motorcycle)
}