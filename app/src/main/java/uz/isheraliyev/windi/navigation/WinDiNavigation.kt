package uz.isheraliyev.windi.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import uz.isheraliyev.core.data.local.AppPreferences
import uz.isheraliyev.core.presenter.utils.safePopBackStack
import uz.isheraliyev.feature.auth.presentation.screen.credentials.CREDENTIALS_ROUTE
import uz.isheraliyev.feature.auth.presentation.screen.credentials.credentialScreen
import uz.isheraliyev.feature.auth.presentation.screen.verification.navigateToVerificationScreen
import uz.isheraliyev.feature.auth.presentation.screen.verification.verificationScreen
import uz.isheraliyev.feature.profile.presentation.screen.edit.editProfileScreen
import uz.isheraliyev.feature.profile.presentation.screen.edit.navigateToEditProfileScreen
import uz.isheraliyev.feature.profile.presentation.screen.profile.PROFILE_ROUTE
import uz.isheraliyev.feature.profile.presentation.screen.profile.navigateToProfileScreen
import uz.isheraliyev.feature.profile.presentation.screen.profile.profileScreen
import uz.isheraliyev.feature.register.presentation.screen.registration.navigateToRegisterScreen
import uz.isheraliyev.feature.register.presentation.screen.registration.registerScreen

@Composable
fun WinDiNavigation(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = if (AppPreferences.isDeviceRegistered) PROFILE_ROUTE else CREDENTIALS_ROUTE
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
            onUpdateProfile = navController::navigateToEditProfileScreen
        )

        editProfileScreen(
            onNavigateBack = navController::safePopBackStack
        )
    }
}