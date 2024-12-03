package uz.isheraliyev.feature.auth.presentation.screen.credentials

data class CredentialsUIState(
    val countryCode: String = "7",
    val numberLength: Int = 10,
    val number: String = "",
    val buttonState: Boolean = false
) {
    fun getFullNumber() = "+$countryCode$number"
}