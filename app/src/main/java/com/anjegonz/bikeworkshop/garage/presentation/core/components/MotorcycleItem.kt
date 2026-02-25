package com.anjegonz.bikeworkshop.garage.presentation.core.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.anjegonz.bikeworkshop.garage.domain.Motorcycle
import com.anjegonz.bikeworkshop.garage.domain.MotorcycleType
import com.anjegonz.bikeworkshop.garage.presentation.core.toImageRes

@Composable
fun MotorcycleItem(
    motorcycle: Motorcycle,
    onClick: () -> Unit,
    isWrappingContent: Boolean,
    modifier: Modifier = Modifier
) {

    Surface(
        shape = RoundedCornerShape(32.dp),
        modifier = modifier
            .clickable(onClick = onClick),
        color = MaterialTheme.colorScheme.surfaceVariant
    ) {
        Row(
            modifier = Modifier
                .then(
                    if (isWrappingContent) Modifier
                        .wrapContentWidth()
                        .padding(12.dp)
                    else Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .height(100.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(motorcycle.type.toImageRes()),
                    contentDescription = motorcycle.type.name,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                )
            }

            Column(
                modifier = Modifier
                    .then(
                        if (!isWrappingContent) Modifier.weight(1f)
                        else Modifier
                    ),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = motorcycle.model,
                    style = MaterialTheme.typography.titleLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = motorcycle.manufacturer,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

            }
        }

    }
}

@Preview
@Composable
private fun PreviewMotorcycleItemRow() {
    val motorcycle = Motorcycle(
        id = 1,
        manufacturer = "Ducati",
        model = "Panigale V4",
        powerPS = 208,
        type = MotorcycleType.SPORT,
        yearOfConstruction = 2021
    )
    MotorcycleItem(
        motorcycle = motorcycle,
        onClick = {},
        true
    )
}
@Preview
@Composable
private fun PreviewMotorcycleItemList() {
    val motorcycle = Motorcycle(
        id = 1,
        manufacturer = "Ducati",
        model = "Panigale V4",
        powerPS = 208,
        type = MotorcycleType.SPORT,
        yearOfConstruction = 2021
    )
    MotorcycleItem(
        motorcycle = motorcycle,
        onClick = {},
        false
    )
}