package uz.isheraliyev.windi.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import uz.isheraliyev.core.presenter.utils.safePopBackStack
import uz.isheraliyev.feature.auth.presentation.screen.credentials.CREDENTIALS_ROUTE
import uz.isheraliyev.feature.auth.presentation.screen.credentials.credentialScreen
import uz.isheraliyev.feature.auth.presentation.screen.verification.navigateToVerificationScreen
import uz.isheraliyev.feature.auth.presentation.screen.verification.verificationScreen
import uz.isheraliyev.feature.register.presentation.screen.registration.navigateToRegisterScreen
import uz.isheraliyev.feature.register.presentation.screen.registration.registerScreen

@Composable
fun WinDiNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = CREDENTIALS_ROUTE
    ) {
        credentialScreen(
            onNext = navController::navigateToVerificationScreen
        )
        verificationScreen(
            onUserFound = { },
            onUserNotFound = navController::navigateToRegisterScreen,
            onNavigateBack = navController::safePopBackStack
        )

        registerScreen(
            onRegistered = { }
        )
    }
}