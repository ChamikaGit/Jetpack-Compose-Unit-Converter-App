package com.chami.composeunitconverterapplication.ui.compose

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.chami.composeunitconverterapplication.ui.ConverterViewModel
import com.chami.composeunitconverterapplication.ui.ConverterViewModelFactory
import com.chami.composeunitconverterapplication.ui.compose.converter.TopScreen
import com.chami.composeunitconverterapplication.ui.compose.history.HistoryScreen
import com.chami.composeunitconverterapplication.data.Conversion
import com.chami.composeunitconverterapplication.data.ConversionResult

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

    val configuration = LocalConfiguration.current
    var isLandscape by remember { mutableStateOf(false) }

    when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {
            isLandscape = true
            LandsScapeView(list, converterViewModel, modifier, historyList, isLandscape)
        }
        Configuration.ORIENTATION_PORTRAIT -> {
            isLandscape = false
            PortraitView(list, converterViewModel, modifier, historyList, isLandscape)
        }
        else -> {
            isLandscape = false
            PortraitView(list, converterViewModel, modifier, historyList, isLandscape)
        }
    }


}

@Composable
fun LandsScapeView(
    list: List<Conversion>,
    converterViewModel: ConverterViewModel,
    modifier: Modifier,
    historyList: State<List<ConversionResult>>,
    isLandscape: Boolean,
) {
    Row(
        modifier = Modifier
            .padding(25.dp)
            .fillMaxSize(), horizontalArrangement = Arrangement.SpaceAround
    ) {

        TopScreen(
            list,
            isLandscape = isLandscape,
            selectedConversion = converterViewModel.selectedConversion,

            ) { message1, message2 ->
            Log.e("MYTAG", "message 1$message1 and message 2 is $message2 ")
            converterViewModel.addResults(message1 = message1, message2 = message2)
        }
        Spacer(modifier = modifier.width(20.dp))
        HistoryScreen(list = historyList, deleteItem = { conversionResult ->
            converterViewModel.deleteResults(conversionResult = conversionResult)
        }, deleteAllItems = {
            converterViewModel.deleteAllResults()
        })
    }
}


@Composable
fun PortraitView(
    list: List<Conversion>,
    converterViewModel: ConverterViewModel,
    modifier: Modifier,
    historyList: State<List<ConversionResult>>,
    isLandscape: Boolean,
) {
    Column(modifier = Modifier.padding(16.dp)) {

        TopScreen(
            list,
            isLandscape = isLandscape,
            selectedConversion = converterViewModel.selectedConversion,

            ) { message1, message2 ->
            Log.e("MYTAG", "message 1$message1 and message 2 is $message2 ")
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