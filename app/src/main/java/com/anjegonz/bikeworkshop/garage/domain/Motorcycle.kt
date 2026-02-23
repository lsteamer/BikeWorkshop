package com.anjegonz.bikeworkshop.garage.domain

data class Motorcycle(
    val id: Int = 0,
    val manufacturer: String,
    val model: String,
    val powerPS: Int,
    val type: Motorcycle.Type = Type.CRUISER,
    val yearOfConstruction : Int
){
    enum class Type{
        CRUISER,
        ADVENTURE,
        SPORT,
        CAFE_RACER
    }
}
