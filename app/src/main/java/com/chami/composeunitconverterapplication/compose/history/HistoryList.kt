package com.chami.composeunitconverterapplication.compose.history

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import com.chami.composeunitconverterapplication.data.ConversionResult

@Composable
fun HistoryList(
    list: State<List<ConversionResult>>,
    onCloseTask: (ConversionResult) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(reverseLayout = true, userScrollEnabled = true) {
        items(items = list.value, key = { item -> item.id }) { item ->

            Log.e("MYTAG", "message 1 ${item.messagePart1} and message 2 is ${item.messagePart2} ")
            HistoryItem(
                messagePart1 = item.messagePart1,
                messagePart2 = item.messagePart2,
                onClose = {
                    onCloseTask(item)
                })

        }

    }
}