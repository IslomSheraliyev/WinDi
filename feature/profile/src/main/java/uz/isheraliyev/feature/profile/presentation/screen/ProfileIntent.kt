package uz.isheraliyev.feature.profile.presentation.screen

sealed interface ProfileIntent {
    data object OnUpdateProfile : ProfileIntent
    data object OnNavigateBack : ProfileIntent
}