package uz.isheraliyev.core.presenter.design.color

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.runtime.structuralEqualityPolicy
import androidx.compose.ui.graphics.Color

class ColorScheme(
    black: Color,
    white: Color,
    labelPrimary: Color,
    labelSecondary: Color,
    secondary: Color,
    tertiary: Color,
    grey: Color,
    border: Color,
    inverseSurface: Color,
    red: Color,
    green: Color
) {
    var black by mutableStateOf(black, structuralEqualityPolicy())
    var white by mutableStateOf(white, structuralEqualityPolicy())
    var labelPrimary by mutableStateOf(labelPrimary, structuralEqualityPolicy())
    var labelSecondary by mutableStateOf(labelSecondary, structuralEqualityPolicy())
    var secondary by mutableStateOf(secondary, structuralEqualityPolicy())
    var tertiary by mutableStateOf(tertiary, structuralEqualityPolicy())
    var grey by mutableStateOf(grey, structuralEqualityPolicy())
    var border by mutableStateOf(border, structuralEqualityPolicy())
    var inverseSurface by mutableStateOf(inverseSurface, structuralEqualityPolicy())
    var red by mutableStateOf(red, structuralEqualityPolicy())
    var green by mutableStateOf(green, structuralEqualityPolicy())
}

fun winDiLight(
    black: Color = WinDiBlack,
    white: Color = WinDiWhite,
    labelPrimary: Color = WinDiLabelPrimary,
    labelSecondary: Color = WinDiLabelSecondary,
    secondary: Color = WinDiSecondary,
    tertiary: Color = WinDiTertiary,
    grey: Color = WinDiGrey,
    border: Color = WinDiBorder,
    inverseSurface: Color = WinDiInverseSurface,
    red: Color = WinDiRed,
    green: Color = WinDiGreen
) = ColorScheme(
    black = black,
    white = white,
    labelPrimary = labelPrimary,
    labelSecondary = labelSecondary,
    secondary = secondary,
    tertiary = tertiary,
    grey = grey,
    border = border,
    inverseSurface = inverseSurface,
    red = red,
    green = green
)

fun winDiBlack(
    black: Color = WinDiBlack,
    white: Color = WinDiWhite,
    labelPrimary: Color = WinDiLabelPrimary,
    labelSecondary: Color = WinDiLabelSecondary,
    secondary: Color = WinDiSecondary,
    tertiary: Color = WinDiTertiary,
    grey: Color = WinDiGrey,
    border: Color = WinDiBorder,
    inverseSurface: Color = WinDiInverseSurface,
    red: Color = WinDiRed,
    green: Color = WinDiGreen
) = ColorScheme(
    black = black,
    white = white,
    labelPrimary = labelPrimary,
    labelSecondary = labelSecondary,
    secondary = secondary,
    tertiary = tertiary,
    grey = grey,
    border = border,
    inverseSurface = inverseSurface,
    red = red,
    green = green
)

val LocalCustomColorScheme = staticCompositionLocalOf { winDiLight() }