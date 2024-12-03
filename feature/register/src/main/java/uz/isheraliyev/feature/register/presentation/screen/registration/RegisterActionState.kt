package uz.isheraliyev.feature.register.presentation.screen.registration

sealed interface RegisterActionState {
    data object Loading : RegisterActionState
    data object Error : RegisterActionState
    data object Success : RegisterActionState
}