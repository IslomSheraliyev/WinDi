package uz.isheraliyev.feature.profile.presentation.screen.edit

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val EDIT_PROFILE_ROUTE = "edit_profile_route"

fun NavGraphBuilder.editProfileScreen(
    onNavigateBack: () -> Unit
) {
    composable(
        route = EDIT_PROFILE_ROUTE
    ) {
        EditProfileRoute(
            onNavigateBack = onNavigateBack
        )
    }
}

fun NavController.navigateToEditProfileScreen() = navigate(EDIT_PROFILE_ROUTE)