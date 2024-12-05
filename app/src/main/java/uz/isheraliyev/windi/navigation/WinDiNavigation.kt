package uz.isheraliyev.windi.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import uz.isheraliyev.core.presenter.utils.safePopBackStack
import uz.isheraliyev.feature.auth.presentation.screen.credentials.credentialScreen
import uz.isheraliyev.feature.auth.presentation.screen.verification.navigateToVerificationScreen
import uz.isheraliyev.feature.auth.presentation.screen.verification.verificationScreen
import uz.isheraliyev.feature.profile.presentation.screen.navigateToProfileScreen
import uz.isheraliyev.feature.profile.presentation.screen.profileScreen
import uz.isheraliyev.feature.register.presentation.screen.registration.navigateToRegisterScreen
import uz.isheraliyev.feature.register.presentation.screen.registration.registerScreen

@Composable
fun WinDiNavigation(startDestination: String) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        credentialScreen(
            onNext = navController::navigateToVerificationScreen
        )
        verificationScreen(
            onUserFound = navController::navigateToProfileScreen,
            onUserNotFound = navController::navigateToRegisterScreen,
            onNavigateBack = navController::safePopBackStack
        )

        registerScreen(
            onRegistered = navController::navigateToProfileScreen
        )

        profileScreen(
            onNavigateBack = navController::safePopBackStack,
            onUpdateProfile = {}
        )
    }
}