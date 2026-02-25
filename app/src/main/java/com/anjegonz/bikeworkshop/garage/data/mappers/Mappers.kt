package com.anjegonz.bikeworkshop.garage.data.mappers

import com.anjegonz.bikeworkshop.garage.data.database.MotorcycleEntity
import com.anjegonz.bikeworkshop.garage.domain.Motorcycle

fun MotorcycleEntity.toDomain(): Motorcycle {
    return Motorcycle(
        id = id,
        manufacturer = manufacturer,
        model = model,
        powerPS = powerPS,
        type = type,
        yearOfConstruction = yearOfConstruction
    )
}

fun Motorcycle.toEntity(): MotorcycleEntity {
    return MotorcycleEntity(
        id = id,
        manufacturer = manufacturer,
        model = model,
        powerPS = powerPS,
        type = type,
        yearOfConstruction = yearOfConstruction
    )
}