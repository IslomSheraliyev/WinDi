package uz.isheraliyev.feature.register.presentation.screen.registration

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
const val REGISTER_ROUTE_BASE = "register_route"
const val REGISTER_ROUTE = "$REGISTER_ROUTE_BASE?$NUMBER={$NUMBER}"

fun NavGraphBuilder.registerScreen(
    onRegistered: () -> Unit,
) {
    composable(
        route = REGISTER_ROUTE,
        enterTransition = { slideInHorizontally(initialOffsetX = { it }) + fadeIn() },
        popEnterTransition = { slideInHorizontally(initialOffsetX = { -it }) + fadeIn() },
        exitTransition = { slideOutHorizontally(targetOffsetX = { -it }) + fadeOut() },
        popExitTransition = { slideOutHorizontally(targetOffsetX = { it }) + fadeOut() },
        arguments = listOf(
            navArgument(NUMBER) { type = NavType.StringType }
        )
    ) { entry ->
        RegisterRoute(
            number = entry.arguments?.getString(NUMBER, "").orEmpty(),
            onRegister = onRegistered
        )
    }
}

fun NavController.navigateToRegisterScreen(number: String) =
    safeNavigate("$REGISTER_ROUTE_BASE?$NUMBER=$number")