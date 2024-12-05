package uz.isheraliyev.feature.profile.presentation.screen.profile

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val PROFILE_ROUTE = "profile_route"

fun NavGraphBuilder.profileScreen(
    onNavigateBack: () -> Unit,
    onUpdateProfile: () -> Unit
) {
    composable(
        route = PROFILE_ROUTE,
        enterTransition = { slideInHorizontally(initialOffsetX = { it }) + fadeIn() },
        popEnterTransition = { slideInHorizontally(initialOffsetX = { -it }) + fadeIn() },
        exitTransition = { slideOutHorizontally(targetOffsetX = { -it }) + fadeOut() },
        popExitTransition = { slideOutHorizontally(targetOffsetX = { it }) + fadeOut() }
    ) {
        ProfileRoute(
            onNavigateBack = onNavigateBack,
            onUpdateProfile = onUpdateProfile
        )
    }
}

fun NavController.navigateToProfileScreen() = navigate(PROFILE_ROUTE)
