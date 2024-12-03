package uz.isheraliyev.feature.auth.presentation.screen.credentials

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val CREDENTIALS_ROUTE = "credentials_route"

fun NavGraphBuilder.credentialScreen(
    onNext: (number: String) -> Unit
) {
    composable(
        route = CREDENTIALS_ROUTE,
        enterTransition = { slideInHorizontally(initialOffsetX = { it }) + fadeIn() },
        popEnterTransition = { slideInHorizontally(initialOffsetX = { -it }) + fadeIn() },
        exitTransition = { slideOutHorizontally(targetOffsetX = { -it }) + fadeOut() },
        popExitTransition = { slideOutHorizontally(targetOffsetX = { it }) + fadeOut() },
    ) {
        CredentialsRoute(onNext = onNext)
    }
}