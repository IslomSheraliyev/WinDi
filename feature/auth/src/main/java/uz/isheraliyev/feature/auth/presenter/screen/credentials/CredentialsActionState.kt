package uz.isheraliyev.feature.auth.presenter.screen.credentials

sealed interface CredentialsActionState {
    data class Success(val number: String) : CredentialsActionState
    data object Loading : CredentialsActionState
    data object Error : CredentialsActionState
}