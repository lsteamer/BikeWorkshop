package com.anjegonz.bikeworkshop.garage.presentation.core

import com.anjegonz.bikeworkshop.R
import com.anjegonz.bikeworkshop.garage.domain.MotorcycleType

fun MotorcycleType.toImageRes(): Int = when (this) {
    MotorcycleType.CRUISER -> R.drawable.cruiser
    MotorcycleType.ADVENTURE -> R.drawable.adventure
    MotorcycleType.SPORT -> R.drawable.sport
    MotorcycleType.CAFE_RACER -> R.drawable.cafe_racer
}