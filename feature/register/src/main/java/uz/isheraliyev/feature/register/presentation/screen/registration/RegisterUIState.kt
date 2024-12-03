package uz.isheraliyev.feature.register.presentation.screen.registration

data class RegisterUIState(
    val number: String = "",
    val name: String = "",
    val username: String = "",
    val buttonState: Boolean = false
)