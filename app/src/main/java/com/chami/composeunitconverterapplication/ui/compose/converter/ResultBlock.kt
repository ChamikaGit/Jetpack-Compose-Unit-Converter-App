package com.chami.composeunitconverterapplication.compose.converter

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ResultBlock(message1: String, message2: String, modifier: Modifier = Modifier,isLandscape : Boolean) {

    if (isLandscape){
        Card(elevation = 18.dp, backgroundColor = Color.DarkGray) {

            Column(modifier = Modifier.padding(10.dp)) {

                Text(text = message1, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = modifier.height(8.dp))
                Text(
                    text = message2,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Blue
                )
            }


        }
    }else{
        Card(elevation = 18.dp, modifier = modifier.fillMaxWidth(1F), backgroundColor = Color.DarkGray) {

            Column(modifier = Modifier.padding(10.dp)) {

                Text(text = message1, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = modifier.height(8.dp))
                Text(
                    text = message2,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Blue
                )
            }
        }
    }



}