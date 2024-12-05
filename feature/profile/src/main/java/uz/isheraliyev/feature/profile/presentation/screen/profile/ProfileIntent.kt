package uz.isheraliyev.feature.profile.presentation.screen.profile

sealed interface ProfileIntent {
    data object OnUpdateProfile : ProfileIntent
    data object OnNavigateBack : ProfileIntent
}