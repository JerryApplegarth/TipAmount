package com.applecompose.tipamount.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.applecompose.tipamount.ui.theme.fontColor
import com.applecompose.tipamount.ui.theme.newBackgroundColor

@Preview
@Composable
fun TopHeader(totalPerPerson: Double = 0.00, modifier: Modifier = Modifier) {
    Surface(
        modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(16.dp)
            //.clip(shape = CircleShape.copy(all = CornerSize(12.dp)))
            .clip(shape = RoundedCornerShape(corner = CornerSize(16.dp))),
        color = MaterialTheme.colors.newBackgroundColor
    ) {

        Column(
            modifier
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            val total = "%.2f".format(totalPerPerson)
            Text(
                text = "Total Per Person",
                style = MaterialTheme.typography.h5
            )
            Text(
                text = "$$total",
                style = MaterialTheme.typography.h3,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.fontColor
            )
        }
    }
}

