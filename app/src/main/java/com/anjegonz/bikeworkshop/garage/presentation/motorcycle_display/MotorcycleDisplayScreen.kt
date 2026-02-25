package com.anjegonz.bikeworkshop.garage.presentation.motorcycle_display

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.anjegonz.bikeworkshop.garage.presentation.motorcycle_display.components.MotorcycleRow
import com.anjegonz.bikeworkshop.ui.theme.BikeWorkshopTheme

@Composable
fun MotorcycleDisplayRoot(
    viewModel: MotorcycleDisplayViewModel = viewModel(),
    motorcycleID: Int,
    manufacturerName: String,
    onNavigateBack: () -> Unit
) {
    LaunchedEffect(motorcycleID, manufacturerName) {
        viewModel.observeMotorcyclesByManufacturer(manufacturerName, motorcycleID)
    }
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.navigateBack.collect {
            onNavigateBack()
        }
    }


    MotorcycleDisplayScreen(
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
fun MotorcycleDisplayScreen(
    state: MotorcycleDisplayState,
    onAction: (MotorcycleDisplayAction) -> Unit,
) {
    Column(
        modifier = Modifier
            .padding(12.dp)
    ) {
        state.mainMotorcycle?.let { motorcycle ->
            MotorcycleInformation(
                motorcycle = motorcycle,
                age = state.ageOfMotorcycle,
                onAction = {
                    onAction(MotorcycleDisplayAction.onMotorcycleDeleteClick)
                }
            )
        }



        Spacer(modifier = Modifier.height(24.dp))
        state.motorcycleCarousel.isNotEmpty().let {
            MotorcycleRow(
                motorcycles = state.motorcycleCarousel,
                onClick = {
                    onAction(MotorcycleDisplayAction.onMotorcycleClick(it))
                },
                modifier = Modifier
            )
        }
    }

}

@Preview
@Composable
private fun Preview() {
    BikeWorkshopTheme {
        MotorcycleDisplayScreen(
            state = MotorcycleDisplayState(),
            onAction = {}
        )
    }
}