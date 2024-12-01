package uz.isheraliyev.feature.auth.data.repository

import uz.isheraliyev.core.domain.error.DataError
import uz.isheraliyev.core.domain.error.Result
import uz.isheraliyev.feature.auth.data.mapper.AuthMapper
import uz.isheraliyev.feature.auth.data.request.CheckAuthCodeRequest
import uz.isheraliyev.feature.auth.data.request.SendAuthCodeRequest
import uz.isheraliyev.feature.auth.data.service.AuthApiService
import uz.isheraliyev.feature.auth.domain.model.CheckAuthCodeModel
import uz.isheraliyev.feature.auth.domain.model.SendAuthCodeModel
import uz.isheraliyev.feature.auth.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val service: AuthApiService
) : AuthRepository {
    override suspend fun sendAuthCode(
        phone: String
    ): Result<SendAuthCodeModel, DataError.Network> {
        return when (val result = service.sendAuthCode(SendAuthCodeRequest(phone))) {
            is Result.Error -> Result.Error(result.error)
            is Result.Success -> Result.Success(result.data.let(AuthMapper.sendAuthCodeMapper))
        }
    }

    override suspend fun checkAuthCode(
        phone: String,
        code: String
    ): Result<CheckAuthCodeModel, DataError.Network> {
        return when (val result = service.checkAuthCode(CheckAuthCodeRequest(phone, code))) {
            is Result.Error -> Result.Error(result.error)
            is Result.Success -> Result.Success(result.data.let(AuthMapper.checkAuthCodeMapper))
        }
    }
}