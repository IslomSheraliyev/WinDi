package uz.isheraliyev.feature.profile.presentation.screen.edit

sealed interface EditProfileActionState {
    data object DataHasBeenSaved : EditProfileActionState
    data object DataHasBeenFetched : EditProfileActionState
    data object Loading : EditProfileActionState
    data object Error : EditProfileActionState
}