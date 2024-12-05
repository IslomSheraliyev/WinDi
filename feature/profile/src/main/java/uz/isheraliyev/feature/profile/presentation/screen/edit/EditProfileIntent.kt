package uz.isheraliyev.feature.profile.presentation.screen.edit

sealed interface EditProfileIntent {
    data object OnNavigateBack : EditProfileIntent
    data class SetName(val name: String) : EditProfileIntent
    data class SetUsername(val username: String) : EditProfileIntent
    data class SetLivingLocation(val location: String) : EditProfileIntent
    data object OnClickDateOfBirth: EditProfileIntent
    data class SetStatus(val status: String) : EditProfileIntent
    data object OnSave : EditProfileIntent
}