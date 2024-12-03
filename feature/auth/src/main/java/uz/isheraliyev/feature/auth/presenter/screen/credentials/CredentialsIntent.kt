package uz.isheraliyev.feature.auth.presenter.screen.credentials

sealed interface CredentialsIntent {
    data class OnChangeNumber(val number: String) : CredentialsIntent
    data class OnChangeCountryCode(val countryCode: String) : CredentialsIntent
    data object OnLogin : CredentialsIntent
}