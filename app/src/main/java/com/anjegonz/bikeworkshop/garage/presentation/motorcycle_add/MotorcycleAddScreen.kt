package com.anjegonz.bikeworkshop.garage.presentation.motorcycle_add

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.anjegonz.bikeworkshop.ui.theme.BikeWorkshopTheme

@Composable
fun MotorcycleAddRoot(
    viewModel: MotorcycleAddViewModel = viewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    MotorcycleAddScreen(
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
fun MotorcycleAddScreen(
    state: MotorcycleAddState,
    onAction: (MotorcycleAddAction) -> Unit,
) {

}
