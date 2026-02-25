package com.anjegonz.bikeworkshop.garage.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.anjegonz.bikeworkshop.garage.domain.MotorcycleType

@Entity
data class MotorcycleEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val manufacturer: String,
    val model: String,
    val powerPS: Int,
    val type: MotorcycleType,
    val yearOfConstruction: Int
)