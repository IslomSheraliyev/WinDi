package uz.isheraliyev.feature.profile.presentation.screen

sealed interface ProfileActionState {
    data object Loading : ProfileActionState
    data object Error : ProfileActionState
    data object Success : ProfileActionState
}