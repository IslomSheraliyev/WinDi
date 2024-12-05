package uz.isheraliyev.windi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import uz.isheraliyev.core.data.local.AppPreferences
import uz.isheraliyev.core.data.service.RefreshApiService
import uz.isheraliyev.core.domain.error.Result

class MainViewModel(
    private val refreshApiService: RefreshApiService
) : ViewModel() {

    private val _splashScreenFlow = MutableStateFlow<Boolean?>(false)
    val splashScreenFlow = _splashScreenFlow.asStateFlow()

    fun checkJWT() = viewModelScope.launch {
        when (refreshApiService.validateAndLoadTokens()) {
            is Result.Error -> refreshToken()
            is Result.Success -> _splashScreenFlow.emit(true)
        }
    }

    private fun refreshToken() = viewModelScope.launch {
        when (val result = refreshApiService.refreshAccessToken()) {
            is Result.Error -> _splashScreenFlow.emit(null)
            is Result.Success -> {
                AppPreferences.accessToken = result.data.accessToken
                AppPreferences.refreshToken = result.data.refreshToken
                _splashScreenFlow.emit(true)
            }
        }
    }
}