package com.chami.composeunitconverterapplication.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.chami.composeunitconverterapplication.ui.compose.BaseScreen
import com.chami.composeunitconverterapplication.ui.theme.ComposeUnitConverterApplicationTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var factory : ConverterViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //we need to pass the converterViewModelFactory that's why we pass the dependencies like this
        //val dao = ConverterDatabase.getInstance(application).converterDAO
        //val repository = ConverterRepositoryImpl(dao)
        //val factory =ConverterViewModelFactory(repository = repository)
        //no need to pass the dependencies like this

        setContent {
            ComposeUnitConverterApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    BaseScreen(factory = factory)
                }
            }
        }
    }
}

