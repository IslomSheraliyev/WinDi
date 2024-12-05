package uz.isheraliyev.feature.profile.data.mapper

import uz.isheraliyev.core.data.mapper.Mapper
import uz.isheraliyev.core.data.mapper.or0
import uz.isheraliyev.core.data.mapper.orFalse
import uz.isheraliyev.feature.profile.data.response.GetMeResponse
import uz.isheraliyev.feature.profile.domain.model.GetMeModel

object GetMeMapper {
    val mapper: Mapper<GetMeResponse?, GetMeModel> = { remote ->
        GetMeModel(
            profileData = remote?.profile_data.let(profileDataMapper)
        )
    }

    private val profileDataMapper: Mapper<GetMeResponse.ProfileData?, GetMeModel.ProfileData> =
        { remote ->
            GetMeModel.ProfileData(
                name = remote?.name.orEmpty(),
                username = remote?.username.orEmpty(),
                birthday = remote?.birthday.orEmpty(),
                city = remote?.city.orEmpty(),
                vk = remote?.vk.orEmpty(),
                instagram = remote?.instagram.orEmpty(),
                status = remote?.status.orEmpty(),
                avatar = remote?.avatar.orEmpty(),
                id = remote?.id.or0(),
                last = remote?.last.orEmpty(),
                online = remote?.online.orFalse(),
                created = remote?.created.orEmpty(),
                phone = remote?.phone.orEmpty(),
                completedTask = remote?.completed_task.or0(),
                avatars = remote?.avatars.let(avatarsMapper)
            )
        }

    private val avatarsMapper: Mapper<GetMeResponse.ProfileData.Avatars?, GetMeModel.ProfileData.Avatars> =
        { remote ->
            GetMeModel.ProfileData.Avatars(
                avatar = remote?.avatar.orEmpty(),
                bigAvatar = remote?.bigAvatar.orEmpty(),
                miniAvatar = remote?.miniAvatar.orEmpty()
            )
        }
}