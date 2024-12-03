package uz.isheraliyev.feature.auth.presenter.component.otp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import uz.isheraliyev.core.presenter.design.theme.WinDiTheme

const val OTP_VIEW_TYPE_UNDERLINE = 1
const val OTP_VIEW_TYPE_BORDER = 2

@Composable
fun OtpView(
    otpText: String,
    isError: Boolean,
    modifier: Modifier = Modifier,
    charColor: Color = WinDiTheme.color.labelPrimary,
    containerColor: Color = Color.Transparent,
    errorContainerColor: Color = WinDiTheme.color.red,
    selectedContainerColor: Color = Color.Transparent,
    charBackground: Color = WinDiTheme.color.white,
    charSize: TextUnit = WinDiTheme.font.bodyMediumMedium.fontSize,
    containerWidth: Dp = charSize.value.dp * 2,
    containerHeight: Dp = charSize.value.dp * 2,
    containerRadius: Dp = 12.dp,
    containerSpacing: Dp = 8.dp,
    otpCount: Int = 6,
    type: Int = OTP_VIEW_TYPE_BORDER,
    enabled: Boolean = true,
    password: Boolean = false,
    passwordChar: String = "",
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.NumberPassword,
        imeAction = ImeAction.Done
    ),
    onOtpTextChange: (String) -> Unit
) {
    BasicTextField(
        modifier = modifier,
        value = otpText,
        onValueChange = {
            if (it.length <= otpCount) {
                onOtpTextChange.invoke(it)
            }
        },
        enabled = enabled,
        keyboardOptions = keyboardOptions,
        decorationBox = {
            Row(horizontalArrangement = Arrangement.spacedBy(containerSpacing)) {
                repeat(otpCount) { index ->
                    CharView(
                        index = index,
                        otpCount = otpCount,
                        text = otpText,
                        isError = isError,
                        charColor = charColor,
                        containerColor = containerColor,
                        errorContainerColor = errorContainerColor,
                        highlightColor = selectedContainerColor,
                        charSize = charSize,
                        containerRadius = containerRadius,
                        containerWidth = containerWidth,
                        containerHeight = containerHeight,
                        type = type,
                        charBackground = charBackground,
                        password = password,
                        passwordChar = passwordChar,
                    )
                }
            }
        }
    )
}
