package uz.isheraliyev.core.presenter.component.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import uz.isheraliyev.core.presenter.design.theme.WinDiTheme

@Composable
fun WinDiButton(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    contentPaddingValues: PaddingValues = PaddingValues(vertical = 16.dp, horizontal = 32.dp),
    containerColor: Color = WinDiTheme.color.black,
    contentColor: Color = WinDiTheme.color.white,
    border: BorderStroke? = null,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(12.dp),
        enabled = enabled,
        modifier = modifier,
        contentPadding = contentPaddingValues,
        border = border,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            disabledContentColor = WinDiTheme.color.grey,
            contentColor = contentColor,
            disabledContainerColor = WinDiTheme.color.secondary,
        )
    ) {
        Text(
            text = text,
            color = if (enabled) contentColor else WinDiTheme.color.grey,
            style = WinDiTheme.font.bodyMediumSemiBold
        )
    }
}