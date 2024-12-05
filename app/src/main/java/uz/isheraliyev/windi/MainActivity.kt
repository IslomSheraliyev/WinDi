package uz.isheraliyev.windi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import uz.isheraliyev.core.presenter.design.theme.WinDiTheme
import uz.isheraliyev.windi.navigation.WinDiNavigation

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val splashScreen = installSplashScreen()
        splashScreen.setKeepOnScreenCondition {
            viewModel.splashScreenFlow.value != true
        }

        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                Color.Transparent.toArgb(),
                Color.Black.toArgb()
            ),
            navigationBarStyle = SystemBarStyle.light(
                Color.Transparent.toArgb(),
                Color.Black.toArgb()
            )
        )

        lifecycleScope.launch { viewModel.checkJWT() }

        setContent {
            WinDiTheme {
                val splashScreenState by viewModel.splashScreenFlow.collectAsState()

                if (splashScreenState != false) {
                    val startDestination = when (splashScreenState) {
                        true -> "PROFILE_ROUTE"
                        else -> "AUTH_ROUTE"
                    }

                    WinDiNavigation(startDestination = startDestination)
                }
            }
        }
    }
}