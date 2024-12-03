package uz.isheraliyev.feature.auth.presenter.component.input

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import uz.isheraliyev.core.presenter.design.theme.WinDiTheme
import uz.isheraliyev.feature.auth.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthCountryCodeTextField(
    countryCode: String,
    onCodeChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val focusedBorderColor = WinDiTheme.color.black
    val unfocusedBorderColor = WinDiTheme.color.border

    var borderColor by remember { mutableStateOf(unfocusedBorderColor) }
    var isFocused by remember { mutableStateOf(false) }

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(WinDiTheme.color.white),
        border = BorderStroke(
            width = 1.dp,
            color = when {
                isFocused -> focusedBorderColor
                else -> unfocusedBorderColor
            }
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            Icon(
                contentDescription = null,
                modifier = Modifier.width(24.dp),
                tint = when (countryCode) {
                    "998" -> Color.Unspecified
                    "7" -> Color.Unspecified
                    else -> WinDiTheme.color.white
                },
                painter = painterResource(
                    when (countryCode) {
                        "998" -> R.drawable.ic_flag_uz
                        else -> R.drawable.ic_flag_ru
                    }
                )
            )

            BasicTextField(
                value = countryCode,
                onValueChange = onCodeChange,
                cursorBrush = SolidColor(WinDiTheme.color.black),
                textStyle = WinDiTheme.font.bodyMediumMedium.copy(WinDiTheme.color.black),
                enabled = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                modifier = Modifier
                    .width(IntrinsicSize.Min)
                    .onFocusChanged {
                        isFocused = it.isFocused
                        borderColor = if (it.isFocused) focusedBorderColor else unfocusedBorderColor
                    },
                decorationBox = { innerTextField ->
                    TextFieldDefaults.DecorationBox(
                        value = countryCode,
                        innerTextField = innerTextField,
                        contentPadding = PaddingValues(0.dp),
                        enabled = true,
                        singleLine = true,
                        visualTransformation = VisualTransformation.None,
                        interactionSource = remember { MutableInteractionSource() },
                        prefix = {
                            Text(
                                text = "+",
                                color = WinDiTheme.color.black,
                                style = WinDiTheme.font.bodyMediumMedium
                            )
                        },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = WinDiTheme.color.white,
                            unfocusedContainerColor = WinDiTheme.color.white,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        )
                    )
                }
            )
        }
    }
}