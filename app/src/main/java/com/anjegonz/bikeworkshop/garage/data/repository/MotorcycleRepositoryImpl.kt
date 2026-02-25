package com.anjegonz.bikeworkshop.garage.data.repository

import androidx.sqlite.SQLiteException
import com.anjegonz.bikeworkshop.garage.data.database.MotorcycleDao
import com.anjegonz.bikeworkshop.garage.data.mappers.toDomain
import com.anjegonz.bikeworkshop.garage.data.mappers.toEntity
import com.anjegonz.bikeworkshop.garage.domain.Motorcycle
import com.anjegonz.bikeworkshop.garage.domain.MotorcycleRepository
import com.anjegonz.bikeworkshop.garage.domain.core.Result
import com.anjegonz.bikeworkshop.garage.domain.core.DataError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class MotorcycleRepositoryImpl(
    private val motorcycleDao: MotorcycleDao
) : MotorcycleRepository {

    override fun getMotorcycles(): Flow<List<Motorcycle>> {
        return motorcycleDao.getMotorcycles()
            .map { entities ->
                entities.map { it.toDomain() }
            }
    }

    override fun getMotorcyclesByManufacturer(manufacturer: String): Flow<List<Motorcycle>> {
        return flow {
            val entities = motorcycleDao.getMotorcyclesByManufacturer(manufacturer)
            emit(entities.map { it.toDomain() })
        }
    }

    override suspend fun insertMotorcycle(motorcycle: Motorcycle) : Result<Unit, DataError.Local> {
        return try {
            motorcycleDao.insert(motorcycle.toEntity())
            Result.Success(Unit)
        } catch (e: SQLiteException){
            Result.Failure(DataError.Local.DISK_FULL)
        }
    }

    override suspend fun deleteMotorcycle(motorcycle: Motorcycle) {
        motorcycleDao.deleteMotorcycle(motorcycle.id)
    }
}