package com.anjegonz.bikeworkshop.garage.presentation.motorcycle_list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.anjegonz.bikeworkshop.R
import com.anjegonz.bikeworkshop.garage.domain.Motorcycle
import com.anjegonz.bikeworkshop.ui.theme.Purple80

@Composable
fun MotorcycleItem(
    motorcycle: Motorcycle,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Surface(
        shape = RoundedCornerShape(32.dp),
        modifier = modifier
            .clickable(onClick = onClick),
        color = Purple80.copy(alpha = 0.2f)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .height(100.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.cruiser),
                    contentDescription = motorcycle.type.name,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .aspectRatio(
                            ratio = 0.65f,
                            matchHeightConstraintsFirst = true
                        )
                )

            }
        }

    }
}