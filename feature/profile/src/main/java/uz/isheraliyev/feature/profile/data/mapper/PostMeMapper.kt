package uz.isheraliyev.feature.profile.data.mapper

import uz.isheraliyev.core.data.mapper.Mapper
import uz.isheraliyev.feature.profile.data.request.PostMeRequest
import uz.isheraliyev.feature.profile.domain.model.PostMeDto

object PostMeMapper {
    val mapper: Mapper<PostMeDto, PostMeRequest> = { local ->
        PostMeRequest(
            name = local.name,
            username = local.username,
            birthday = local.birthday,
            city = local.city,
            status = local.status,
            avatar = local.avatar.let(avatarMapper)
        )
    }

    private val avatarMapper: Mapper<PostMeDto.Avatar, PostMeRequest.Avatar> = { local ->
        PostMeRequest.Avatar(
            filename = local.filename,
            base_64 = local.base_64
        )
    }
}