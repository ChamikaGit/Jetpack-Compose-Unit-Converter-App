
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.chami.composeunitconverterapplication.data.Conversion
import com.chami.composeunitconverterapplication.R

@Composable
fun ConversionMenu(
    list: List<Conversion>,
    isLandscape : Boolean,
    modifier: Modifier = Modifier,
    itemConvert: (Conversion) -> Unit
) {

    var displayingText by rememberSaveable { mutableStateOf("Select a conversion type") }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }
    var isExpanded by remember { mutableStateOf(false) }

    val icon = if (isExpanded) {
        Icons.Filled.KeyboardArrowUp
    } else {
        Icons.Filled.KeyboardArrowDown
    }

    Column {

        if(isLandscape) {
            OutlinedTextField(
                value = displayingText,
                onValueChange = {
                    displayingText = it
                },
                textStyle = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold),
                modifier = modifier
                    .clickable {
                        isExpanded = !isExpanded
                    }
                    .onGloballyPositioned { coordinates ->
                        textFieldSize = coordinates.size.toSize()
                    },
                label = {
                    Text(text = "Conversion Type")
                },
                trailingIcon = {
                    Icon(
                        imageVector = icon, contentDescription = "icon"
                    )
                },
                readOnly = true,
                enabled = false,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    disabledTextColor = MaterialTheme.colors.onSurface,
                    disabledBorderColor = colorResource(R.color.purple_500),
                    disabledPlaceholderColor = MaterialTheme.colors.onPrimary,
                    disabledLabelColor = Color.White,
                    //For Icons
                    disabledLeadingIconColor = Color.White,
                    disabledTrailingIconColor = Color.White
                )

            )
        }else{
            OutlinedTextField(
                value = displayingText,
                onValueChange = {
                    displayingText = it
                },
                textStyle = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold),
                modifier = modifier
                    .fillMaxWidth()
                    .clickable {
                        isExpanded = !isExpanded
                    }
                    .onGloballyPositioned { coordinates ->
                        textFieldSize = coordinates.size.toSize()
                    },
                label = {
                    Text(text = "Conversion Type")
                },
                trailingIcon = {
                    Icon(
                        imageVector = icon, contentDescription = "icon"
                    )
                },
                readOnly = true,
                enabled = false,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    disabledTextColor = MaterialTheme.colors.onSurface,
                    disabledBorderColor = colorResource(R.color.purple_500),
                    disabledPlaceholderColor = MaterialTheme.colors.onPrimary,
                    disabledLabelColor = Color.White,
                    //For Icons
                    disabledLeadingIconColor = Color.White,
                    disabledTrailingIconColor = Color.White
                )

            )
        }

        Spacer(modifier = modifier.height(5.dp))

        DropdownMenu(expanded = isExpanded, onDismissRequest = {
            isExpanded = false
        }, modifier = modifier.width(with(LocalDensity.current) {
            textFieldSize.width.toDp()
        })) {
            list.forEach { conversion ->
                DropdownMenuItem(onClick = {
                    displayingText = conversion.description
                    isExpanded = false
                    itemConvert(conversion)
                }) {

                    Text(
                        text = conversion.description,
                        fontSize = 16.sp, fontWeight = FontWeight.Normal
                    )
                }
            }
        }
    }


}