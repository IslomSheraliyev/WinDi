package uz.isheraliyev.core.presenter.design.font

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle

data class FontScheme(
    val headingLargeSemiBold: TextStyle,
    val captionSmallRegular: TextStyle,
    val bodyMediumMedium: TextStyle,
    val bodySmallRegular: TextStyle,
    val bodyMediumSemiBold: TextStyle,
    val bodyMediumRegular: TextStyle
)

val LocalCustomTypography = staticCompositionLocalOf { typography }
