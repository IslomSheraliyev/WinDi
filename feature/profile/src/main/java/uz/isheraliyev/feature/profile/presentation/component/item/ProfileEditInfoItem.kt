package uz.isheraliyev.feature.profile.presentation.component.item

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import uz.isheraliyev.core.presenter.design.theme.WinDiTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileEditInfoItem(
    value: String,
    title: String,
    onValueChange: (String) -> Unit,
    enabled: Boolean = true,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(WinDiTheme.color.secondary)
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = title,
            color = WinDiTheme.color.black,
            style = WinDiTheme.font.bodyMediumMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            cursorBrush = SolidColor(WinDiTheme.color.black),
            textStyle = WinDiTheme.font.captionSmallRegular.copy(WinDiTheme.color.grey),
            enabled = enabled,
            modifier = Modifier.fillMaxWidth(),
            decorationBox = { innerTextField ->
                TextFieldDefaults.DecorationBox(
                    value = value,
                    innerTextField = innerTextField,
                    contentPadding = PaddingValues(0.dp),
                    enabled = true,
                    singleLine = true,
                    visualTransformation = VisualTransformation.None,
                    interactionSource = remember { MutableInteractionSource() },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = WinDiTheme.color.secondary,
                        unfocusedContainerColor = WinDiTheme.color.secondary,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )
            }
        )
    }
}