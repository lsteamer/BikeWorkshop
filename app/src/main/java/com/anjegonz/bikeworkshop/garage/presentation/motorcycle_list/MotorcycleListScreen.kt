package com.anjegonz.bikeworkshop.garage.presentation.motorcycle_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.anjegonz.bikeworkshop.garage.domain.MotorcycleType
import com.anjegonz.bikeworkshop.garage.presentation.core.MotorcycleUIModel
import com.anjegonz.bikeworkshop.garage.presentation.motorcycle_list.components.MotorcycleList

@Composable
fun MotorcycleListRoot(
    viewModel: MotorcycleListViewModel = viewModel(),
    onNavigation: (MotorcycleUIModel?) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    //Root is different from screen because that way we can test the screen without needing a viewModel
    MotorcycleListScreen(
        state = state,
        onAction = { action ->
            when (action) {
                is MotorcycleListAction.OnMotorcycleClick -> {
                    onNavigation(action.motorcycle)
                }
            }

        }
    )
}

@Composable
fun MotorcycleListScreen(
    state: MotorcycleListState,
    onAction: (MotorcycleListAction) -> Unit,
) {

    Column(
        modifier = Modifier
            .statusBarsPadding()
    ) {

        val lazyListState = rememberLazyListState()
        if (state.isLoading) {
            //Loading indicator if time permits
        } else if (state.motorcycles.isEmpty()) {
            //A nice image and an "empty screen" if time permits
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = "Get more Motorcycles in your Garage!",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(horizontal = 32.dp)
                )
            }
        } else {
            MotorcycleList(
                motorcycles = state.motorcycles,
                onClick = {
                    onAction(MotorcycleListAction.OnMotorcycleClick(it))
                },
                modifier = Modifier
                    .fillMaxSize(),
                scrollState = lazyListState
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun PreviewMotorcycleListScreenEmpty() {
    MotorcycleListScreen(
        state = MotorcycleListState(isLoading = false, motorcycles = emptyList()),
        onAction = {}
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewMotorcycleListScreenWithData() {
    MotorcycleListScreen(
        state = MotorcycleListState(
            isLoading = false,
            motorcycles = listOf(
                MotorcycleUIModel(
                    id = 1,
                    manufacturer = "Ducati",
                    model = "Panigale V4",
                    powerPS = 208,
                    type = MotorcycleType.SPORT,
                    yearOfConstruction = 2021,
                    age = 4
                ),
                MotorcycleUIModel(
                    id = 2,
                    manufacturer = "BMW",
                    model = "R 1250 GS",
                    powerPS = 136,
                    type = MotorcycleType.ADVENTURE,
                    yearOfConstruction = 2020,
                    age = 5
                ),
                MotorcycleUIModel(
                    id = 3,
                    manufacturer = "Harley-Davidson",
                    model = "Softail Standard",
                    powerPS = 95,
                    type = MotorcycleType.CRUISER,
                    yearOfConstruction = 2019,
                    age = 6
                )
            )
        ),
        onAction = {}
    )
}