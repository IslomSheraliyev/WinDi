package uz.isheraliyev.feature.profile.presentation.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uz.isheraliyev.core.domain.error.Result
import uz.isheraliyev.feature.profile.domain.usecase.GetMeUseCase

class ProfileViewModel(
    private val getMeUseCase: GetMeUseCase
) : ViewModel() {
    private val _actionState = MutableSharedFlow<ProfileActionState>()
    val actionState = _actionState.asSharedFlow()

    private val _uiState = MutableStateFlow(ProfileUIState())
    val uiState = _uiState.asStateFlow()

    fun getMe() = viewModelScope.launch {
        _actionState.emit(ProfileActionState.Loading)
        when (val result = getMeUseCase()) {
            is Result.Error -> _actionState.emit(ProfileActionState.Error)
            is Result.Success -> {
                _uiState.update { it.copy(userDate = result.data) }
                _actionState.emit(ProfileActionState.Success)
            }
        }
    }
}