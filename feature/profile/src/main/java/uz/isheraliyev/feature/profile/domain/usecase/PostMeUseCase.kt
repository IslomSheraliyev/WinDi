package uz.isheraliyev.feature.profile.domain.usecase

import uz.isheraliyev.core.domain.error.DataError
import uz.isheraliyev.core.domain.error.Result
import uz.isheraliyev.feature.profile.domain.model.PostMeDto
import uz.isheraliyev.feature.profile.domain.repository.ProfileRepository

class PostMeUseCase(
    private val repository: ProfileRepository
) {
    suspend operator fun invoke(request: PostMeDto): Result<Unit, DataError.Network> =
        repository.postMe(request)
}