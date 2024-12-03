package uz.isheraliyev.feature.auth.presenter.component.otp

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import uz.isheraliyev.core.presenter.design.theme.WinDiTheme

@Composable
fun CharView(
    index: Int,
    otpCount: Int,
    text: String,
    isError: Boolean,
    charColor: Color,
    highlightColor: Color,
    containerColor: Color,
    errorContainerColor: Color,
    charSize: TextUnit,
    containerWidth: Dp,
    containerHeight: Dp,
    containerRadius: Dp,
    type: Int = OTP_VIEW_TYPE_UNDERLINE,
    charBackground: Color = Color.Transparent,
    password: Boolean = false,
    passwordChar: String = ""
) {
    val containerColor2 =
        if (isError) errorContainerColor
        else if (index == text.length || (index == otpCount - 1 && text.length == otpCount)) highlightColor
        else containerColor

    val modifier = if (type == OTP_VIEW_TYPE_BORDER) Modifier
        .size(containerWidth, containerHeight)
        .border(
            width = 1.dp,
            color = containerColor2,
            shape = RoundedCornerShape(containerRadius)
        )
        .clip(RoundedCornerShape(containerRadius))
        .background(charBackground)
    else Modifier
        .width(containerWidth)
        .height(containerHeight)
        .background(charBackground)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        val char = when {
            index >= text.length -> ""
            password -> passwordChar
            else -> text[index].toString()
        }
        Text(
            text = char,
            color = charColor,
            modifier = modifier.wrapContentHeight(),
            style = WinDiTheme.font.bodyMediumMedium,
            fontSize = charSize,
            textAlign = TextAlign.Center,
        )
        if (type == OTP_VIEW_TYPE_UNDERLINE) {
            Spacer(modifier = Modifier.height(2.dp))

            Box(
                modifier = Modifier
                    .background(charColor)
                    .height(1.dp)
                    .width(containerWidth)
                    .height(containerHeight)
            )
        }
    }
}