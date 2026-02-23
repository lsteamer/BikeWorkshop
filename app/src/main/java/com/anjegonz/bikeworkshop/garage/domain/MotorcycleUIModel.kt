package com.anjegonz.bikeworkshop.garage.domain

data class MotorcycleUIModel(
    val id: Int,
    val manufacturer: String,
    val model: String,
    val powerPS: Int,
    val type: MotorcycleType = MotorcycleType.CRUISER,
    val yearOfConstruction : Int,
    val age: Int
)