package com.anjegonz.bikeworkshop.garage.presentation.motorcycle_display.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.anjegonz.bikeworkshop.garage.domain.Motorcycle
import com.anjegonz.bikeworkshop.garage.domain.MotorcycleType
import com.anjegonz.bikeworkshop.garage.presentation.core.components.MotorcycleItem


@Composable
fun MotorcycleRow(
    motorcycles: List<Motorcycle>,
    onClick: (Motorcycle) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier,
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
                true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MotorcycleRowPreview() {
    MaterialTheme {
        MotorcycleRow(
            motorcycles = listOf(
                Motorcycle(
                    id = 1,
                    manufacturer = "Yamaha",
                    model = "MT-07",
                    powerPS = 75,
                    type = MotorcycleType.SPORT,
                    yearOfConstruction = 2023
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
                    model = "Softail",
                    powerPS = 95,
                    type = MotorcycleType.CRUISER,
                    yearOfConstruction = 2019
                )
            ),
            onClick = {}
        )
    }
}