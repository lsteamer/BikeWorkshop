package com.anjegonz.bikeworkshop.garage.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MotorcycleDao {

    @Insert
    suspend fun insert(motorcycle: MotorcycleEntity)

    @Query("SELECT * FROM MotorcycleEntity ORDER BY manufacturer ASC, model ASC")
    fun getMotorcycles(): Flow<List<MotorcycleEntity>>

    @Query("SELECT * FROM MotorcycleEntity WHERE manufacturer = :manufacturer")
    suspend fun getMotorcyclesByManufacturer(manufacturer: String): List<MotorcycleEntity>

    @Query("DELETE FROM MotorcycleEntity WHERE id = :id")
    suspend fun deleteMotorcycle(id: Int)
}