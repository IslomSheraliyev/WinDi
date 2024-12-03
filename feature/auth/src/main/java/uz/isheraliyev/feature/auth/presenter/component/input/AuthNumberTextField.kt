package uz.isheraliyev.feature.auth.presenter.component.input

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import uz.isheraliyev.core.presenter.design.theme.WinDiTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthNumberTextField(
    number: String,
    placeholder: String,
    onNumberChange: (String) -> Unit,
    modifier: Modifier = Modifier,
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
        Column(
            modifier = Modifier
                .padding(
                    horizontal = 16.dp,
                    vertical = if (isFocused || number.isNotBlank()) 8.dp else 16.dp,
                )
        ) {
            if ((isFocused || number.isNotBlank())) Text(
                text = placeholder,
                color = WinDiTheme.color.grey,
                style = WinDiTheme.font.captionSmallRegular
            )

            BasicTextField(
                value = number,
                onValueChange = onNumberChange,
                cursorBrush = SolidColor(WinDiTheme.color.black),
                textStyle = WinDiTheme.font.bodyMediumMedium.copy(WinDiTheme.color.black),
                enabled = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                modifier = Modifier
                    .fillMaxSize()
                    .onFocusChanged {
                        isFocused = it.isFocused
                        borderColor = if (it.isFocused) focusedBorderColor else unfocusedBorderColor
                    },
                decorationBox = { innerTextField ->
                    TextFieldDefaults.DecorationBox(
                        value = number,
                        innerTextField = innerTextField,
                        contentPadding = PaddingValues(0.dp),
                        enabled = true,
                        singleLine = true,
                        visualTransformation = VisualTransformation.None,
                        interactionSource = remember { MutableInteractionSource() },
                        placeholder = {
                            if (isFocused.not()) Text(
                                text = placeholder,
                                color = WinDiTheme.color.grey,
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