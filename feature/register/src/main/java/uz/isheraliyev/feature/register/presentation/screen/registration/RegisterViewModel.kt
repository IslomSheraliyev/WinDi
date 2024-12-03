package uz.isheraliyev.feature.register.presentation.screen.registration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uz.isheraliyev.core.domain.error.Result
import uz.isheraliyev.feature.register.domain.usecase.RegisterUseCase

class RegisterViewModel(
    private val registerUseCase: RegisterUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(RegisterUIState())
    val uiState = _uiState.asStateFlow()

    private val _actionState = MutableSharedFlow<RegisterActionState>()
    val actionState = _actionState.asSharedFlow()

    fun setName(name: String) {
        if (
            name.length < 40 &&
            name.all { it.isLetter() }
        ) _uiState.update {
            it.copy(
                name = name,
                buttonState = name.isNotEmpty() && uiState.value.username.isNotEmpty()
            )
        }
    }

    fun setUsername(username: String) {
        if (
            username.length < 40 &&
            containsOnlyBasicLatinLetters(username)
        ) _uiState.update {
            it.copy(
                username = username,
                buttonState = uiState.value.name.isNotEmpty() && username.isNotEmpty()
            )
        }
    }

    fun setNumber(number: String) {
        _uiState.update { it.copy(number = number) }
    }

    fun register() = viewModelScope.launch {
        _actionState.emit(RegisterActionState.Loading)
        when (
            registerUseCase(
                phone = uiState.value.number,
                name = uiState.value.name,
                username = uiState.value.username
            )
        ) {
            is Result.Error -> _actionState.emit(RegisterActionState.Error)
            is Result.Success -> _actionState.emit(RegisterActionState.Success)
        }
    }

    private fun containsOnlyBasicLatinLetters(input: String): Boolean {
        return input.matches(Regex("^[A-Za-z]+$"))
    }
}