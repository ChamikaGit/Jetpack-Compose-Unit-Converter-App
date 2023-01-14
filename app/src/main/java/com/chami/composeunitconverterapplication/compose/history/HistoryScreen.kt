package com.chami.composeunitconverterapplication.compose.history

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chami.composeunitconverterapplication.data.ConversionResult

@Composable
fun HistoryScreen(
    modifier: Modifier = Modifier,
    list: State<List<ConversionResult>>,
    deleteItem: (ConversionResult) -> Unit,
    deleteAllItems: () -> Unit
) {
    Column(
        modifier = modifier
            .background(color = Color.DarkGray)
    ) {

        if (list.value.isNotEmpty()) {
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Text(
                    text = "History",
                    modifier = modifier.align(Alignment.CenterStart),
                    maxLines = 1,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = MaterialTheme.colors.secondary
                )
                OutlinedButton(
                    onClick = { deleteAllItems() },
                    modifier = Modifier.align(Alignment.CenterEnd),
                    colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.White)
                ) {
                    Text(
                        text = "Clear All",
                        maxLines = 1,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                }

            }

            HistoryList(list = list, onCloseTask = { conversionResult ->
                deleteItem(conversionResult)
            })
        }
    }
}