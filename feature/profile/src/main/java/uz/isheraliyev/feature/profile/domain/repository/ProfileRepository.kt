package uz.isheraliyev.feature.profile.domain.repository

import uz.isheraliyev.core.domain.error.DataError
import uz.isheraliyev.core.domain.error.Result
import uz.isheraliyev.feature.profile.domain.model.GetMeModel
import uz.isheraliyev.feature.profile.domain.model.PostMeDto

interface ProfileRepository {
    suspend fun getMe(): Result<GetMeModel, DataError.Network>
    suspend fun postMe(request: PostMeDto): Result<Unit, DataError.Network>
}