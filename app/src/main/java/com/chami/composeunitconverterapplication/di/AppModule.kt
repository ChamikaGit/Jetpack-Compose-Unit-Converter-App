package com.chami.composeunitconverterapplication.di

import android.app.Application
import androidx.room.Room
import com.chami.composeunitconverterapplication.data.ConverterDatabase
import com.chami.composeunitconverterapplication.data.ConverterRepository
import com.chami.composeunitconverterapplication.data.ConverterRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providerConverterDatabase(app: Application): ConverterDatabase {
        return Room.databaseBuilder(app, ConverterDatabase::class.java,
            "converter_data_database").build()
    }

    //ConverterRepository is an interface and used it with it's impl class and also use ConverterDatabase(above already created that dependency)
    // to get the converterDAO to complete the ConverterRepository's dependency graph
    @Provides
    @Singleton
    fun providerConverterRepository(db : ConverterDatabase) : ConverterRepository{
        return ConverterRepositoryImpl(db.converterDAO)
    }
}