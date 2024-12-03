package uz.isheraliyev.core.presenter.design.theme

import android.os.Build
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import uz.isheraliyev.core.presenter.design.color.WinDiBlack
import uz.isheraliyev.core.presenter.design.color.WinDiSecondary
import uz.isheraliyev.core.presenter.design.color.WinDiTertiary
import uz.isheraliyev.core.presenter.design.color.WinDiWhite
import uz.isheraliyev.core.presenter.design.color.winDiLight
import uz.isheraliyev.core.presenter.design.font.typography

private val colorPaletteL = lightColorScheme(
    primary = WinDiBlack,
    secondary = WinDiSecondary,
    tertiary = WinDiTertiary,
    background = WinDiWhite
)

private val darkColorPaletteL = darkColorScheme(
    primary = WinDiBlack,
    secondary = WinDiSecondary,
    tertiary = WinDiTertiary,
    background = WinDiWhite
)

@Composable
fun WinDiTheme(
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            dynamicDarkColorScheme(context)
        }

        else -> colorPaletteL

    }

    WinDiCustomTheme(
        color = winDiLight(),
        typography = typography
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            content = content
        )
    }
}