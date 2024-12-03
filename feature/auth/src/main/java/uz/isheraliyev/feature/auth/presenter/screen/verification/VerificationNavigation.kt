package uz.isheraliyev.feature.auth.presenter.screen.verification

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import uz.isheraliyev.core.presenter.utils.safeNavigate

const val NUMBER = "number"
const val VERIFICATION_ROUTE_BASE = "verification_route"
const val VERIFICATION_ROUTE = "$VERIFICATION_ROUTE_BASE?$NUMBER={$NUMBER}"

fun NavGraphBuilder.verificationScreen(
    onUserFound: () -> Unit,
    onUserNotFound: () -> Unit,
    onNavigateBack: () -> Unit
) {
    composable(
        route = VERIFICATION_ROUTE,
        enterTransition = { slideInHorizontally(initialOffsetX = { it }) + fadeIn() },
        popEnterTransition = { slideInHorizontally(initialOffsetX = { -it }) + fadeIn() },
        exitTransition = { slideOutHorizontally(targetOffsetX = { -it }) + fadeOut() },
        popExitTransition = { slideOutHorizontally(targetOffsetX = { it }) + fadeOut() },
        arguments = listOf(
            navArgument(NUMBER) { type = NavType.StringType }
        )
    ) { entry ->
        VerificationRoute(
            number = entry.arguments?.getString(NUMBER).orEmpty(),
            onUserFound = onUserFound,
            onUserNotFound = onUserNotFound,
            onNavigateBack = onNavigateBack
        )
    }
}

fun NavController.navigateToVerificationScreen(number: String) =
    safeNavigate("$VERIFICATION_ROUTE_BASE?$NUMBER=$number")
