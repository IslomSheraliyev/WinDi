package uz.isheraliyev.feature.register.domain.repository

import uz.isheraliyev.core.domain.error.DataError
import uz.isheraliyev.core.domain.error.Result
import uz.isheraliyev.feature.register.domain.model.RegisterModel

interface RegisterRepository {
    suspend fun register(
        phone: String,
        name: String,
        username: String
    ): Result<RegisterModel, DataError.Network>
}