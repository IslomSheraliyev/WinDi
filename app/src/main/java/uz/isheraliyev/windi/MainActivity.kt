package uz.isheraliyev.windi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.compose.getKoin
import uz.isheraliyev.core.data.local.AppPreferences
import uz.isheraliyev.core.data.local.NavigationManager
import uz.isheraliyev.core.presenter.design.theme.WinDiTheme
import uz.isheraliyev.feature.auth.presentation.screen.credentials.CREDENTIALS_ROUTE
import uz.isheraliyev.windi.navigation.WinDiNavigation

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

        setContent {
            val navController = rememberNavController()
            val navigationManager: NavigationManager = getKoin().get()
            navigationManager.setNavController(navController)

            val mainViewModel: MainViewModel by viewModel()
            LaunchedEffect(Unit) {
                mainViewModel.authChannelFlow.collectLatest {
                    AppPreferences.isDeviceRegistered = false
                    navController.navigate(CREDENTIALS_ROUTE)
                }
            }

            WinDiTheme {
                WinDiNavigation(navController = navController)
            }
        }
    }
}