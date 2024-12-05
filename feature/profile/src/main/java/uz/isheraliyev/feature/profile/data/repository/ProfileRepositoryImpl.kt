package uz.isheraliyev.feature.profile.data.repository

import uz.isheraliyev.core.domain.error.DataError
import uz.isheraliyev.core.domain.error.Result
import uz.isheraliyev.feature.profile.data.mapper.GetMeMapper
import uz.isheraliyev.feature.profile.data.mapper.PostMeMapper
import uz.isheraliyev.feature.profile.data.service.ProfileApiService
import uz.isheraliyev.feature.profile.domain.model.GetMeModel
import uz.isheraliyev.feature.profile.domain.model.PostMeDto
import uz.isheraliyev.feature.profile.domain.repository.ProfileRepository

class ProfileRepositoryImpl(
    private val service: ProfileApiService
) : ProfileRepository {
    override suspend fun getMe(): Result<GetMeModel, DataError.Network> {
        return when (val result = service.getMe()) {
            is Result.Error -> Result.Error(result.error)
            is Result.Success -> Result.Success(result.data.let(GetMeMapper.mapper))
        }
    }

    override suspend fun postMe(request: PostMeDto): Result<Unit, DataError.Network> {
        return when (val result = service.postMe(request.let(PostMeMapper.mapper))) {
            is Result.Error -> Result.Error(result.error)
            is Result.Success -> Result.Success(Unit)
        }
    }
}