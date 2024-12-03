package uz.isheraliyev.feature.register.presentation.screen.registration

sealed interface RegisterIntent {
    data class OnChangeName(val name: String) : RegisterIntent
    data class OnChangeUsername(val username: String) : RegisterIntent
    data object Register : RegisterIntent
}