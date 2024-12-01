package uz.isheraliyev.feature.auth.domain.usecase

import uz.isheraliyev.feature.auth.domain.repository.AuthRepository

class CheckAuthCodeUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(phone: String, code: String) = repository.checkAuthCode(phone, code)
}