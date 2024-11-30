package uz.isheraliyev.core.presenter.design.font

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import uz.isheraliyev.core.design.R

val typography = FontScheme(
    headingLargeSemiBold = TextStyle(
        fontWeight = FontWeight.W600,
        fontSize = 24.sp,
        lineHeight = 28.sp,
        fontFamily = FontFamily(Font(R.font.roboto))
    ),
    captionSmallRegular = TextStyle(
        fontWeight = FontWeight.W400,
        fontSize = 12.sp,
        lineHeight = 14.sp,
        fontFamily = FontFamily(Font(R.font.roboto))
    ),
    bodyMediumMedium = TextStyle(
        fontWeight = FontWeight.W500,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        fontFamily = FontFamily(Font(R.font.roboto))
    ),
    bodySmallRegular = TextStyle(
        fontWeight = FontWeight.W400,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        fontFamily = FontFamily(Font(R.font.roboto))
    ),
    bodyMediumSemiBold = TextStyle(
        fontWeight = FontWeight.W600,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        fontFamily = FontFamily(Font(R.font.roboto))
    ),
    bodyMediumRegular = TextStyle(
        fontWeight = FontWeight.W400,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        fontFamily = FontFamily(Font(R.font.roboto))
    )
)