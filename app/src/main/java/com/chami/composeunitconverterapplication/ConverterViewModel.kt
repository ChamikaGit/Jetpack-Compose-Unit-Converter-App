package com.chami.composeunitconverterapplication

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chami.composeunitconverterapplication.data.Conversion
import com.chami.composeunitconverterapplication.data.ConversionResult
import com.chami.composeunitconverterapplication.data.ConverterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ConverterViewModel(private val repository: ConverterRepository) : ViewModel() {

    //we can't use remember{} block her because it's not inside of compose
    val selectedConversion: MutableState<Conversion?> =  mutableStateOf(null)
    val inputText: MutableState<String> =  mutableStateOf("")
    val typedValue =  mutableStateOf("0.0")

    fun getConversionList(): List<Conversion> {
        return listOf(
            Conversion(
                id = 1,
                description = "Pounds to kilogram",
                convertFrom = "lbs",
                convertTo = "kg",
                multipleBy = 0.453592
            ), Conversion(
                id = 2,
                description = "Kilogram to Pound",
                convertFrom = "kg",
                convertTo = "lbs",
                multipleBy = 2.20462
            ), Conversion(
                id = 3,
                description = "Yards to Meters",
                convertFrom = "yd",
                convertTo = "m",
                multipleBy = 0.9144
            ), Conversion(
                id = 3,
                description = "Meters to Yards",
                convertFrom = "m",
                convertTo = "yd",
                multipleBy = 1.09361
            ), Conversion(
                id = 3,
                description = "Miles to Kilometers",
                convertFrom = "mi",
                convertTo = "km",
                multipleBy = 1.60934
            ), Conversion(
                id = 3,
                description = "Kilometers to Miles",
                convertFrom = "km",
                convertTo = "mi",
                multipleBy = 0.621371
            )
        )
    }

    fun addResults(message1: String, message2: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertResults(
                ConversionResult(
                    0,
                    messagePart1 = message1,
                    messagePart2 = message2
                )
            )
        }
    }

    fun deleteResults(conversionResult: ConversionResult) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteResults(conversionResult = conversionResult)
        }
    }

    fun deleteAllResults() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllResults()
        }
    }

    //no need to run this inside a coroutine because this function already run by room on background worker thread
    val getAllResults = repository.getSaveResults()


}