package uz.isheraliyev.feature.auth.presentation.screen.verification

data class VerificationUIState(
    val otp: String = "",
    val number: String = "",
    val isError: Boolean = false
)
