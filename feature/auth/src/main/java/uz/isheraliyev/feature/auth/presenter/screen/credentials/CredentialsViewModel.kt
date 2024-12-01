package uz.isheraliyev.feature.auth.presenter.screen.credentials

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uz.isheraliyev.core.domain.error.Result
import uz.isheraliyev.feature.auth.domain.usecase.SendAuthCodeUseCase

class CredentialsViewModel(
    private val sendAuthCodeUseCase: SendAuthCodeUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(CredentialsUIState())
    val uiState = _uiState.asStateFlow()

    private val _actionState = MutableSharedFlow<CredentialsActionState>()
    val actionState = _actionState.asSharedFlow()

    fun sendAuthCode() = viewModelScope.launch {
        _actionState.emit(CredentialsActionState.Loading)
        when (sendAuthCodeUseCase(uiState.value.getFullNumber())) {
            is Result.Error -> _actionState.emit(CredentialsActionState.Error)
            is Result.Success -> _actionState.emit(CredentialsActionState.Success(uiState.value.getFullNumber()))
        }
    }

    fun setNumber(newNumber: String) {
        if (
            uiState.value.countryCode in listOf("7", "998") &&
            newNumber.length <= uiState.value.numberLength &&
            newNumber.all { it.isDigit() }
        ) _uiState.update {
            it.copy(
                number = newNumber,
                buttonState = newNumber.length == uiState.value.numberLength
            )
        }
    }

    fun setCountryCode(newCountryCode: String) {
        if (
            newCountryCode.length <= 3 &&
            newCountryCode.all { it.isDigit() }
        ) _uiState.update {
            it.copy(
                countryCode = newCountryCode,
                numberLength = when (newCountryCode) {
                    "7" -> 10
                    "998" -> 9
                    else -> 0
                }
            )
        }
    }
}