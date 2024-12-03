package uz.isheraliyev.feature.auth.presentation.screen.credentials

sealed interface CredentialsActionState {
    data class Success(val number: String) : CredentialsActionState
    data object Loading : CredentialsActionState
    data object Error : CredentialsActionState
}