package uz.isheraliyev.feature.register.presentation.component.input

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import uz.isheraliyev.core.presenter.design.theme.WinDiTheme

@Composable
fun NameTextField(
    name: String,
    onNameChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = name,
        onValueChange = onNameChange,
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        textStyle = WinDiTheme.font.bodyMediumMedium.copy(color = WinDiTheme.color.black),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = WinDiTheme.color.white,
            unfocusedContainerColor = WinDiTheme.color.white,
            disabledContainerColor = WinDiTheme.color.white,
            errorContainerColor = WinDiTheme.color.white,

            focusedBorderColor = WinDiTheme.color.black,
            unfocusedBorderColor = WinDiTheme.color.border,
            disabledBorderColor = WinDiTheme.color.border,
            errorBorderColor = WinDiTheme.color.border
        )
    )
}