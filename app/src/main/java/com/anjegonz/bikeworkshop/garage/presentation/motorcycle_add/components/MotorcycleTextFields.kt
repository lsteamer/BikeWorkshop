package com.anjegonz.bikeworkshop.garage.presentation.motorcycle_add.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.anjegonz.bikeworkshop.garage.domain.MotorcycleType
import com.anjegonz.bikeworkshop.garage.presentation.motorcycle_add.MotorcycleAddAction

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MotorcycleTextFields(
    modifier: Modifier = Modifier,
    manufacturerText: String,
    modelText: String,
    powerPS: String,
    motorcycleType: MotorcycleType = MotorcycleType.CRUISER,
    yearOfConstructionValue: String,
    onAction: (MotorcycleAddAction) -> Unit
){


    var dropDownMenuExpanded by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = manufacturerText,
        onValueChange = {
            onAction(MotorcycleAddAction.OnManufacturerTextFieldChange(it))
        },
        label = { Text("Manufacturer") },
    )
    OutlinedTextField(
        value = modelText,
        onValueChange = {
            onAction(MotorcycleAddAction.OnModelTextFieldChange(it))
        },
        label = { Text("Model") },
    )
    OutlinedTextField(
        value = powerPS,
        onValueChange = {
            if (it.all { char -> char.isDigit() }) {
                onAction(MotorcycleAddAction.OnPowerPSTextFieldChange(it))
            }
        },
        label = { Text("PowerPS") },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done
        ),

    )


    ExposedDropdownMenuBox(
        expanded = dropDownMenuExpanded,
        onExpandedChange = { dropDownMenuExpanded = it },
        modifier = modifier
    ) {

        OutlinedTextField(
            modifier = Modifier.menuAnchor(),
            value = motorcycleType.name,
            onValueChange = {},
            readOnly = true,
            label = {
                Text("MotorcycleType")
            },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = dropDownMenuExpanded)
            },
            colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors()
        )
        ExposedDropdownMenu(
            expanded = dropDownMenuExpanded,
            onDismissRequest = { dropDownMenuExpanded = false }
        ) {
            MotorcycleType.entries.forEach { type ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = type.name,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    },
                    onClick = {
                        onAction(MotorcycleAddAction.OnTypeDropdownMenuChange(type))
                        dropDownMenuExpanded = false
                    },
                    leadingIcon = {
                        if (type == motorcycleType) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = null,
                            )
                        }
                    }
                )
            }
        }
    }

    OutlinedTextField(
        value = yearOfConstructionValue,
        onValueChange = {
            if (it.all { char -> char.isDigit() }) {
                onAction(MotorcycleAddAction.OnYearTextFieldChange(it))
            }
        },
        label = { Text("Year of Construction") },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done
        ),
    )
}

@Preview(showBackground = true)
@Composable
private fun MotorcycleTextFieldsPreview() {
    MaterialTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            MotorcycleTextFields(
                manufacturerText = "Yamaha",
                modelText = "MT-07",
                powerPS = "75",
                motorcycleType = MotorcycleType.SPORT,
                yearOfConstructionValue = "2023",
                onAction = {}
            )
        }
    }
}