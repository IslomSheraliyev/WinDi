package uz.isheraliyev.core.data.response

import kotlinx.serialization.Serializable

@Serializable
data class RefreshTokenResponse(
    val access_token: String,
    val refresh_token: String
)