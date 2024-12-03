package uz.isheraliyev.feature.register.data.repository

import uz.isheraliyev.core.domain.error.DataError
import uz.isheraliyev.core.domain.error.Result
import uz.isheraliyev.feature.register.data.mapper.RegisterMapper
import uz.isheraliyev.feature.register.data.request.RegisterRequest
import uz.isheraliyev.feature.register.data.service.RegisterApiService
import uz.isheraliyev.feature.register.domain.model.RegisterModel
import uz.isheraliyev.feature.register.domain.repository.RegisterRepository

class RegisterRepositoryImpl(
    private val service: RegisterApiService
) : RegisterRepository {
    override suspend fun register(
        phone: String,
        name: String,
        username: String
    ): Result<RegisterModel, DataError.Network> {
        return when (val result = service.register(
            RegisterRequest(
                phone = phone,
                name = name,
                username = username
            )
        )) {
            is Result.Error -> Result.Error(result.error)
            is Result.Success -> Result.Success(result.data.let(RegisterMapper.mapper))
        }
    }
}