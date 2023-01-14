package com.chami.composeunitconverterapplication.compose

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.chami.composeunitconverterapplication.ConverterViewModel
import com.chami.composeunitconverterapplication.ConverterViewModelFactory
import com.chami.composeunitconverterapplication.compose.converter.TopScreen
import com.chami.composeunitconverterapplication.compose.history.HistoryScreen

//recommended best practice to increase the re-usability - (modifier: Modifier = Modifier)
//@Preview(name = "BaseScreen")
@Composable
fun BaseScreen(
    factory: ConverterViewModelFactory,
    modifier: Modifier = Modifier,
    converterViewModel: ConverterViewModel = viewModel(factory = factory)
) {

    val list = converterViewModel.getConversionList()
    val historyList = converterViewModel.getAllResults.collectAsState(initial = emptyList())

    Column(modifier = Modifier.padding(16.dp)) {

        TopScreen(
            list,
            selectedConversion = converterViewModel.selectedConversion,

        ) { message1, message2 ->
            Log.e("MYTAG", "message 1$message1 and message 2 is $message2 "  )
            converterViewModel.addResults(message1 = message1, message2 = message2)
        }
        Spacer(modifier = modifier.height(20.dp))
        HistoryScreen(list = historyList, deleteItem = { conversionResult ->
            converterViewModel.deleteResults(conversionResult = conversionResult)
        }, deleteAllItems = {
            converterViewModel.deleteAllResults()
        })
    }

}