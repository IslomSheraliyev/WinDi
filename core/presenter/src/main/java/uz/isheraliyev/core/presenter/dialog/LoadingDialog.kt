package uz.isheraliyev.core.presenter.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import uz.isheraliyev.core.presenter.design.theme.WinDiTheme

@Composable
fun LoadingDialog(
    isVisible: Boolean
) {
    if (isVisible) Dialog(
        onDismissRequest = {},
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false,
            usePlatformDefaultWidth = false
        )
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
                .background(color = Color.Black.copy(alpha = 0.7f))
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(120.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(WinDiTheme.color.secondary)
            ) {
                CircularProgressIndicator(
                    color = WinDiTheme.color.labelPrimary,
                    strokeWidth = 4.dp,
                    trackColor = WinDiTheme.color.white,
                    strokeCap = StrokeCap.Square
                )
            }
        }
    }
}