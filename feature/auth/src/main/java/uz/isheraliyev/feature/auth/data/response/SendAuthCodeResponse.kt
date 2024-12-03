package uz.isheraliyev.feature.auth.data.response

import kotlinx.serialization.Serializable

@Serializable
data class SendAuthCodeResponse(
    val is_success: Boolean?
)