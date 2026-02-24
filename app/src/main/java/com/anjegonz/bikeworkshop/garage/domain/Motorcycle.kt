package com.anjegonz.bikeworkshop.garage.domain

data class Motorcycle(
    val id: Int = 0,
    val manufacturer: String,
    val model: String,
    val powerPS: Int,
    val type: MotorcycleType,
    val yearOfConstruction: Int
)