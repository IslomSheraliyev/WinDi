package uz.isheraliyev.feature.auth.domain.usecase

import uz.isheraliyev.feature.auth.domain.repository.AuthRepository

class SendAuthCodeUseCase(
    private val repository: AuthRepository
) {

    suspend operator fun invoke(phone: String) = repository.sendAuthCode(phone)
}