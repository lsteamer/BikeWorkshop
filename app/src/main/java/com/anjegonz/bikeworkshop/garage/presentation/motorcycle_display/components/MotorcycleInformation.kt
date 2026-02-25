package com.anjegonz.bikeworkshop.garage.presentation.motorcycle_display.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.anjegonz.bikeworkshop.garage.domain.Motorcycle
import com.anjegonz.bikeworkshop.garage.domain.MotorcycleType
import com.anjegonz.bikeworkshop.garage.presentation.core.toImageRes

@Composable
fun MotorcycleInformation(
    motorcycle: Motorcycle,
    age: Int,
    onAction: () -> Unit
) {
    Surface(
        shape = RoundedCornerShape(32.dp),
        color = MaterialTheme.colorScheme.surfaceVariant
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Column(
                //I'm trying this column to take as much height as possible but I've spent a lot on this
                //and time is ticking to keep this under the 4 horus. Imma come back to this later
                modifier = Modifier
                    .height(IntrinsicSize.Min)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Image(
                    painter = painterResource(motorcycle.type.toImageRes()),
                    contentDescription = motorcycle.type.name,
                    contentScale = ContentScale.None,
                    modifier = Modifier.align(Alignment.CenterHorizontally)

                )

                IconButton(
                    onClick = onAction,
                    modifier = Modifier
                        .align(Alignment.End)
                        .size(100.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Remove item",
                        tint = Color.Red,
                        modifier = Modifier.size(80.dp)
                    )
                }

            }

            Column(
                modifier = Modifier
                    .weight(1f),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.End
            ) {
                //Change this to outline time permitting
                Text(
                    text = "Model:",
                    style = MaterialTheme.typography.labelMedium,
                )
                Text(
                    text = motorcycle.model,
                    style = MaterialTheme.typography.titleLarge,
                )
                Spacer(
                    modifier = Modifier.padding(8.dp)
                )
                Text(
                    text = "Manufacturer:",
                    style = MaterialTheme.typography.labelMedium,
                )
                Text(
                    text = motorcycle.manufacturer,
                    style = MaterialTheme.typography.titleMedium,
                )
                Spacer(
                    modifier = Modifier.padding(8.dp)
                )
                Text(
                    text = "Type:",
                    style = MaterialTheme.typography.titleMedium,
                )
                Text(
                    text = motorcycle.type.name,
                    style = MaterialTheme.typography.titleMedium,
                )
                Spacer(
                    modifier = Modifier.padding(8.dp)
                )
                Text(
                    text = "Power:",
                    style = MaterialTheme.typography.labelMedium,
                )
                Text(
                    text = "${motorcycle.powerPS} PS",
                    style = MaterialTheme.typography.titleSmall,
                )
                Spacer(
                    modifier = Modifier.padding(8.dp)
                )
                Text(
                    text = "Year of Construction:",
                    style = MaterialTheme.typography.labelMedium,
                )
                Text(
                    text = motorcycle.yearOfConstruction.toString(),
                    style = MaterialTheme.typography.titleSmall,
                )
                Text(
                    text = "Age:",
                    style = MaterialTheme.typography.labelMedium,
                )
                Text(
                    text = "$age years",
                    style = MaterialTheme.typography.titleSmall,
                )

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MotorcycleInformationPreview() {
    MaterialTheme {
        Column(modifier = Modifier.padding(16.dp)) {
            MotorcycleInformation(
                motorcycle = Motorcycle(
                    id = 1,
                    manufacturer = "Yamaha",
                    model = "MT-07",
                    powerPS = 75,
                    type = MotorcycleType.SPORT,
                    yearOfConstruction = 2023
                ),
                age = 1,
                onAction = {}
            )
        }
    }
}