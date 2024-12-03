package uz.isheraliyev.feature.auth.data.request

import kotlinx.serialization.Serializable

@Serializable
data class SendAuthCodeRequest(
    val phone: String
)
