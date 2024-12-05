package uz.isheraliyev.feature.profile.presentation.screen.edit

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.threeten.bp.LocalDate
import uz.isheraliyev.core.domain.error.Result
import uz.isheraliyev.feature.profile.domain.model.PostMeDto
import uz.isheraliyev.feature.profile.domain.usecase.GetMeUseCase
import uz.isheraliyev.feature.profile.domain.usecase.PostMeUseCase
import uz.isheraliyev.feature.profile.presentation.utils.formatWithDashesDMY

class EditProfileViewModel(
    private val getMeUseCase: GetMeUseCase,
    private val postMeUseCase: PostMeUseCase
) : ViewModel() {

    private val _actionState = MutableSharedFlow<EditProfileActionState>()
    val actionState = _actionState.asSharedFlow()

    private val _uiState = MutableStateFlow(EditProfileUIState())
    val uiState = _uiState.asStateFlow()

    fun getMe() = viewModelScope.launch {
        _actionState.emit(EditProfileActionState.Loading)
        when (val result = getMeUseCase()) {
            is Result.Error -> _actionState.emit(EditProfileActionState.Error)
            is Result.Success -> {
                _uiState.update {
                    it.copy(
                        name = result.data.profileData.name,
                        username = result.data.profileData.username,
                        status = result.data.profileData.status,
                        livingLocation = result.data.profileData.city,
                        dateOfBirth = LocalDate.parse(result.data.profileData.birthday)
                    )
                }
                _actionState.emit(EditProfileActionState.DataHasBeenFetched)
            }
        }
    }

    fun postMe() = viewModelScope.launch {
        _actionState.emit(EditProfileActionState.Loading)
        when (val result = postMeUseCase(
            request = PostMeDto(
                name = uiState.value.name,
                username = uiState.value.username,
                birthday = uiState.value.dateOfBirth.formatWithDashesDMY(),
                city = uiState.value.livingLocation,
                status = uiState.value.status,
                avatar = PostMeDto.Avatar(
                    filename = uiState.value.filename,
                    base_64 = uiState.value.base64
                )
            )
        )) {
            is Result.Error -> _actionState.emit(EditProfileActionState.Error)
            is Result.Success -> _actionState.emit(EditProfileActionState.DataHasBeenSaved)
        }
    }

    fun updateUIState(field: EditProfileField, value: Any) {
        _uiState.value = when (field) {
            EditProfileField.Name -> _uiState.value.copy(name = value as String)
            EditProfileField.Username -> _uiState.value.copy(username = value as String)
            EditProfileField.Status -> _uiState.value.copy(status = value as String)
            EditProfileField.LivingLocation -> _uiState.value.copy(livingLocation = value as String)
            EditProfileField.DateOfBirth -> _uiState.value.copy(dateOfBirth = value as LocalDate)
            EditProfileField.Filename -> _uiState.value.copy(filename = value as String)
            EditProfileField.Base64 -> _uiState.value.copy(base64 = value as String)
        }
    }
}