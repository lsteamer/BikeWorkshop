package com.anjegonz.bikeworkshop.garage.presentation.motorcycle_list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.anjegonz.bikeworkshop.ui.theme.BikeWorkshopTheme

@Composable
fun MotorcycleListRoot(
    viewModel: MotorcycleListViewModel = viewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    MotorcycleListScreen(
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
fun MotorcycleListScreen(
    state: MotorcycleListState,
    onAction: (MotorcycleListAction) -> Unit,
) {

}

@Preview
@Composable
private fun Preview() {
    BikeWorkshopTheme {
        MotorcycleListScreen(
            state = MotorcycleListState(),
            onAction = {}
        )
    }
}