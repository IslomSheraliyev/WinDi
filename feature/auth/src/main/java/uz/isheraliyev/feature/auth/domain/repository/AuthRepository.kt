package uz.isheraliyev.feature.auth.domain.repository

import uz.isheraliyev.core.domain.error.DataError
import uz.isheraliyev.core.domain.error.Result
import uz.isheraliyev.feature.auth.domain.model.CheckAuthCodeModel
import uz.isheraliyev.feature.auth.domain.model.SendAuthCodeModel

interface AuthRepository {

    suspend fun sendAuthCode(
        phone: String
    ): Result<SendAuthCodeModel, DataError.Network>

    suspend fun checkAuthCode(
        phone: String,
        code: String
    ): Result<CheckAuthCodeModel, DataError.Network>
}