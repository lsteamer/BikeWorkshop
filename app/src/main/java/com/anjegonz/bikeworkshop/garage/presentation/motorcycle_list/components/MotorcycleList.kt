package com.anjegonz.bikeworkshop.garage.presentation.motorcycle_list.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.anjegonz.bikeworkshop.garage.domain.Motorcycle
import com.anjegonz.bikeworkshop.garage.domain.MotorcycleType
import com.anjegonz.bikeworkshop.garage.presentation.core.components.MotorcycleItem

@Composable
fun MotorcycleList(
    motorcycles: List<Motorcycle>,
    onClick: (Motorcycle) -> Unit,
    modifier: Modifier = Modifier,
    scrollState: LazyListState = rememberLazyListState()
) {
    LazyColumn(
        modifier = modifier,
        state = scrollState,
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(
            items = motorcycles,
            key = { it.id }
        ) { motorcycle ->
            MotorcycleItem(
                motorcycle = motorcycle,
                onClick = {
                    onClick(motorcycle)
                },
                false,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
            )
        }
    }
}

@Preview
@Composable
private fun PreviewMotorcycleList() {
    val motorcycles = listOf(
        Motorcycle(
            id = 1,
            manufacturer = "Ducati",
            model = "Panigale V4",
            powerPS = 208,
            type = MotorcycleType.SPORT,
            yearOfConstruction = 2021
        ),
        Motorcycle(
            id = 2,
            manufacturer = "BMW",
            model = "R 1250 GS",
            powerPS = 136,
            type = MotorcycleType.ADVENTURE,
            yearOfConstruction = 2020
        ),
        Motorcycle(
            id = 3,
            manufacturer = "Harley-Davidson",
            model = "Softail Standard",
            powerPS = 95,
            type = MotorcycleType.CRUISER,
            yearOfConstruction = 2019
        ),
        Motorcycle(
            id = 4,
            manufacturer = "Triumph",
            model = "Thruxton RS",
            powerPS = 105,
            type = MotorcycleType.CAFE_RACER,
            yearOfConstruction = 2022
        )
    )
    MotorcycleList(
        motorcycles = motorcycles,
        onClick = {}
    )
}