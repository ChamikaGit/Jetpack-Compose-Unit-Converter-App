package com.chami.composeunitconverterapplication.data

import kotlinx.coroutines.flow.Flow

interface ConverterRepository {

    suspend fun insertResults(conversionResult: ConversionResult)
    suspend fun deleteResults(conversionResult: ConversionResult)
    suspend fun deleteAllResults()
    fun getSaveResults(): Flow<List<ConversionResult>>

}