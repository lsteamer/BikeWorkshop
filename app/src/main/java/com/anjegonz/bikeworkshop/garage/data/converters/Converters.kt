package com.anjegonz.bikeworkshop.garage.data.converters

import androidx.room.TypeConverter
import com.anjegonz.bikeworkshop.garage.domain.MotorcycleType

object Converters {

    @TypeConverter
    fun fromMotorcycleType(type: MotorcycleType): String = type.name

    @TypeConverter
    fun toMotorcycleType(name: String): MotorcycleType = enumValueOf(name)
}