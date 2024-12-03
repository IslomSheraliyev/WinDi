package uz.isheraliyev.feature.auth.presentation.screen.verification

sealed interface VerificationActionState {
    data object Loading : VerificationActionState
    data object ResendError : VerificationActionState
    data object VerificationError : VerificationActionState
    data object ResendSuccess : VerificationActionState
    data class VerificationSuccess(val isUserExists: Boolean) : VerificationActionState
}