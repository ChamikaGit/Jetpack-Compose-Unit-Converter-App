package com.chami.composeunitconverterapplication.ui.compose.history

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HistoryItem(messagePart1: String, messagePart2: String, modifier: Modifier = Modifier,onClose:()->Unit) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .border(border = BorderStroke(0.5.dp, Color.Gray))
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier.weight(0.85f)) {
            Text(
                text = messagePart1,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color.Black
            )
            Spacer(modifier = modifier.height(8.dp))
            Text(
                text = messagePart2,
                fontWeight = FontWeight.Normal,
                fontSize = 18.sp,
                color = Color.LightGray
            )

        }

        IconButton(onClick = { onClose() },modifier.weight(0.15f)) {
            Icon(Icons.Filled.Close, contentDescription = "close")
        }

    }
}

@Preview(name = "HistoryItem")
@Composable
fun ItemPreview() {
    HistoryItem("sasas","aaasasas"){

    }
}