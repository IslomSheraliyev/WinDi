package uz.isheraliyev.feature.register.data.mapper

import uz.isheraliyev.core.data.mapper.Mapper
import uz.isheraliyev.core.data.mapper.or0
import uz.isheraliyev.feature.register.data.response.RegisterResponse
import uz.isheraliyev.feature.register.domain.model.RegisterModel

object RegisterMapper {
    val mapper: Mapper<RegisterResponse?, RegisterModel> = { remote ->
        RegisterModel(
            refreshToken = remote?.refresh_token.orEmpty(),
            accessToken = remote?.access_token.orEmpty(),
            userId = remote?.user_id.or0()
        )
    }
}