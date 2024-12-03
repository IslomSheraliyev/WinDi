package uz.isheraliyev.feature.auth.data.mapper

import uz.isheraliyev.core.data.mapper.Mapper
import uz.isheraliyev.core.data.mapper.or0
import uz.isheraliyev.core.data.mapper.orFalse
import uz.isheraliyev.feature.auth.data.response.CheckAuthCodeResponse
import uz.isheraliyev.feature.auth.data.response.SendAuthCodeResponse
import uz.isheraliyev.feature.auth.domain.model.CheckAuthCodeModel
import uz.isheraliyev.feature.auth.domain.model.SendAuthCodeModel

object AuthMapper {
    val sendAuthCodeMapper: Mapper<SendAuthCodeResponse?, SendAuthCodeModel> = { remote ->
        SendAuthCodeModel(
            isSuccess = remote?.is_success.orFalse()
        )
    }

    val checkAuthCodeMapper: Mapper<CheckAuthCodeResponse?, CheckAuthCodeModel> = { remote ->
        CheckAuthCodeModel(
            refreshToken = remote?.refresh_token.orEmpty(),
            accessToken = remote?.access_token.orEmpty(),
            userId = remote?.userId.or0(),
            isUserExists = remote?.is_user_exists.orFalse()
        )
    }
}