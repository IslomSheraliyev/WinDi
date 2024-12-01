package uz.isheraliyev.feature.auth.presenter.screen.verification

sealed interface VerificationIntent {
    data object OnNavigateBack : VerificationIntent
    data object ResendAuthCode : VerificationIntent
    data class OnOtpChange(val otp: String) : VerificationIntent
}