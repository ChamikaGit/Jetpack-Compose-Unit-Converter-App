package com.chami.composeunitconverterapplication.compose.converter

import ConversionMenu
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chami.composeunitconverterapplication.data.Conversion
import java.math.RoundingMode
import java.text.DecimalFormat

@Composable
fun TopScreen(
    list: List<Conversion>,
    isLandscape: Boolean,
    modifier: Modifier = Modifier,
    selectedConversion: MutableState<Conversion?>,
    save: (String, String) -> Unit
) {

//    val selectedConversion: MutableState<Conversion?> = remember { mutableStateOf(null) }
    val inputText: MutableState<String> = rememberSaveable { mutableStateOf("") }
    val typedValue = rememberSaveable { mutableStateOf("0.0") }

    var isAdded by rememberSaveable { mutableStateOf(false) }


    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {

        ConversionMenu(
            list = list,
            isLandscape = isLandscape
        ) {
            selectedConversion.value = it
            typedValue.value = "0.0"
        }
        selectedConversion.value?.let {
            InputFieldBlock(
                conversion = it,
                isLandscape = isLandscape,
                inputText = inputText
            ) { calculateInput ->
                typedValue.value = calculateInput
                isAdded = true
            }
        }

        //If return value is not a decimal number we change it to decimal format
        //And calculate the final results
        if (typedValue.value != "0.0") {
            val input = typedValue.value.toDouble()
            val multiply = selectedConversion.value!!.multipleBy
            val results = input * multiply

            //Rounding of the result to 4 decimal places
            val df = DecimalFormat("#.####")
            df.roundingMode = RoundingMode.DOWN
            val roundResult = df.format(results)

            val message1 =
                "${typedValue.value} ${selectedConversion.value!!.convertFrom} is equal to"
            val message2 = "$roundResult ${selectedConversion.value!!.convertTo}"

            if (isAdded) {
                save(message1, message2)
                isAdded = false
            }
            Spacer(modifier = modifier.height(16.dp))
            ResultBlock(message1 = message1, message2 = message2, isLandscape = isLandscape)
        }
    }
}