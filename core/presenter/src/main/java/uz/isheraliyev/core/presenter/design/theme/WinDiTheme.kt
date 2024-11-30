package uz.isheraliyev.core.presenter.design.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import uz.isheraliyev.core.presenter.design.color.ColorScheme
import uz.isheraliyev.core.presenter.design.color.LocalCustomColorScheme
import uz.isheraliyev.core.presenter.design.font.FontScheme
import uz.isheraliyev.core.presenter.design.font.LocalCustomTypography

object WinDiTheme {
    val color: ColorScheme
        @Composable
        @ReadOnlyComposable
        get() = LocalCustomColorScheme.current

    val font: FontScheme
        @Composable
        @ReadOnlyComposable
        get() = LocalCustomTypography.current
}

@Composable
fun WinDiCustomTheme(
    color: ColorScheme,
    typography: FontScheme,
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalCustomColorScheme provides color,
        LocalCustomTypography provides typography
    ) {
        content()
    }
}