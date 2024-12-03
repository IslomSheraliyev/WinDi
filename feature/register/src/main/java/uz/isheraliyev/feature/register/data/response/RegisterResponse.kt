package uz.isheraliyev.feature.register.data.response

import kotlinx.serialization.Serializable

@Serializable
data class RegisterResponse(
    val refresh_token: String?,
    val access_token: String?,
    val user_id: Int?
)