package uz.isheraliyev.feature.auth.data.response

import kotlinx.serialization.Serializable

@Serializable
data class CheckAuthCodeResponse(
    val refresh_token: String?,
    val access_token: String?,
    val userId: Int?,
    val is_user_exists: Boolean?
)