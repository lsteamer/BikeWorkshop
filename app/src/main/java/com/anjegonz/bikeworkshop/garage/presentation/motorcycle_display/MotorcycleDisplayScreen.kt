package com.anjegonz.bikeworkshop.garage.presentation.motorcycle_display

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.anjegonz.bikeworkshop.ui.theme.BikeWorkshopTheme

@Composable
fun MotorcycleDisplayRoot(
    viewModel: MotorcycleDisplayViewModel = viewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

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