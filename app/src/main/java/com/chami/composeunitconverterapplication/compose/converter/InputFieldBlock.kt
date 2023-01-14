package com.chami.composeunitconverterapplication.compose.converter

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chami.composeunitconverterapplication.data.Conversion

@Composable
fun InputFieldBlock(
    conversion: Conversion,
    inputText: MutableState<String>,
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current,
    calculate: (String) -> Unit
) {
    Column(modifier = modifier.padding(top = 30.dp)) {
        Row(modifier = modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            TextField(
                value = inputText.value, onValueChange = {
                    inputText.value = it
                }, modifier = modifier.fillMaxWidth(0.65F),
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.None,
                    autoCorrect = true,
                    keyboardType = KeyboardType.Number
                ),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = MaterialTheme.colors.surface.copy(
                        alpha = 0.3F
                    )
                ),
                textStyle = TextStyle(color = Color.DarkGray, fontSize = 16.sp),
                maxLines = 1
            )

            Text(
                text = conversion.convertFrom,
                fontSize = 14.sp,
                modifier = modifier
                    .fillMaxWidth(0.35F)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedButton(onClick = {
            if (inputText.value.isNotEmpty()) {
                calculate(inputText.value)
            } else {
                Toast.makeText(context, "Please, enter a value", Toast.LENGTH_SHORT).show()
            }

        }, modifier = Modifier.fillMaxWidth(1F).height(42.dp)) {

            Text(
                text = "Convert",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color.Blue
            )
        }
    }


//    @Preview(name = "InPutFieldBlock")
//    @Composable
//    fun InputFieldBlockPreview(){
//        val inputText: MutableState<String> = remember { mutableStateOf("") }
//        InputFieldBlock(Conversion(1,"asa","asas","sdsd",0.0),inputText){
//
//        }
//    }


}