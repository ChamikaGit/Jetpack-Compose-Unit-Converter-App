package com.chami.composeunitconverterapplication.data

import kotlinx.coroutines.flow.Flow

class ConverterRepositoryImpl(private val converterDAO: ConverterDAO) : ConverterRepository {

    override suspend fun insertResults(conversionResult: ConversionResult) {
        converterDAO.insertResults(conversionResult = conversionResult)
    }

    override suspend fun deleteResults(conversionResult: ConversionResult) {
        converterDAO.deleteResults(conversionResult = conversionResult)
    }

    override suspend fun deleteAllResults() {
        converterDAO.deleteAll()
    }

    override fun getSaveResults(): Flow<List<ConversionResult>> = converterDAO.getResults()

}