package uz.isheraliyev.feature.profile.domain.usecase

import uz.isheraliyev.feature.profile.domain.repository.ProfileRepository

class GetMeUseCase(
    private val repository: ProfileRepository
) {
    suspend operator fun invoke() = repository.getMe()
}