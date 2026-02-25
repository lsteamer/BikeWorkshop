package com.anjegonz.bikeworkshop.garage.presentation.motorcycle_add

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.anjegonz.bikeworkshop.garage.domain.MotorcycleType
import com.anjegonz.bikeworkshop.garage.presentation.motorcycle_add.components.MotorcycleTextFields

@Composable
fun MotorcycleAddRoot(
    viewModel: MotorcycleAddViewModel = viewModel(),
    onNavigateBack: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.navigateBack.collect {
            onNavigateBack()
        }
    }

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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        MotorcycleTextFields(
            manufacturerText = state.manufacturerText,
            modelText = state.modelText,
            powerPS = state.powerPS,
            motorcycleType = state.motorcycleType,
            yearOfConstructionValue = state.yearOfConstructionValue,
            onAction = onAction,
            modifier = Modifier
        )

        //TODO before calimoto Fix this from crashing
        //Here. I'll clean this up for a bit.
        Button(
            onClick = { onAction(MotorcycleAddAction.OnClickOnSubmit) },
            modifier = Modifier.fillMaxWidth(),
            enabled = state.areAllFieldsFilled
        ) {
            Text("Add new Motorcycle")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MotorcycleAddScreenPreview() {
    MaterialTheme {
        MotorcycleAddScreen(
            state = MotorcycleAddState(
                manufacturerText = "Yamaha",
                modelText = "MT-07",
                powerPS = "75",
                motorcycleType = MotorcycleType.SPORT,
                yearOfConstructionValue = "2023"
            ),
            onAction = {}
        )
    }
}