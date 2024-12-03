package uz.isheraliyev.core.data.request

import kotlinx.serialization.Serializable

@Serializable
data class RefreshTokenRequest(
    val refresh_token: String
)