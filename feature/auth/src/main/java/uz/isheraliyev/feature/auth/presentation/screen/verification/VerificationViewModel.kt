package uz.isheraliyev.feature.auth.presentation.screen.verification

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import uz.isheraliyev.core.data.local.AppPreferences
import uz.isheraliyev.core.domain.error.Result
import uz.isheraliyev.feature.auth.domain.usecase.CheckAuthCodeUseCase
import uz.isheraliyev.feature.auth.domain.usecase.SendAuthCodeUseCase
import kotlin.time.Duration.Companion.seconds

class VerificationViewModel(
    private val sendAuthCodeUseCase: SendAuthCodeUseCase,
    private val checkAuthCodeUseCase: CheckAuthCodeUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(VerificationUIState())
    val uiState = _uiState.asStateFlow()

    private val _actionState = MutableSharedFlow<VerificationActionState>()
    val actionState = _actionState.asSharedFlow()

    fun setNumber(number: String) = _uiState.update { it.copy(number = number) }

    fun resendAuthCode() = viewModelScope.launch {
        _uiState.update { it.copy(otp = "") }
        _actionState.emit(VerificationActionState.Loading)
        when (sendAuthCodeUseCase(uiState.value.number)) {
            is Result.Error -> _actionState.emit(VerificationActionState.ResendError)
            is Result.Success -> _actionState.emit(VerificationActionState.ResendSuccess)
        }
    }

    fun setOtp(otp: String) = viewModelScope.launch {
        if (otp.length <= 6 && otp.all { it.isDigit() })
            _uiState.update { it.copy(otp = otp, isError = false) }

        if (otp.length == 6) {
            _actionState.emit(VerificationActionState.Loading)
            when (val result = checkAuthCodeUseCase(uiState.value.number, uiState.value.otp)) {
                is Result.Error -> {
                    _uiState.update { it.copy(isError = true) }
                    _actionState.emit(VerificationActionState.VerificationError)
                }

                is Result.Success -> {
                    AppPreferences.accessToken = result.data.accessToken
                    AppPreferences.refreshToken = result.data.refreshToken
                    _actionState.emit(VerificationActionState.VerificationSuccess(result.data.isUserExists))
                }
            }
        }
    }

    fun countDownTimer(countDown: Int) = flow {
        for (seconds in countDown downTo 0) {
            delay(1.seconds)
            emit(seconds)
            if (!currentCoroutineContext().isActive) return@flow
        }
    }
}