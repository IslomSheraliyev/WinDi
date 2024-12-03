package uz.isheraliyev.feature.auth.data.request

import kotlinx.serialization.Serializable

@Serializable
data class CheckAuthCodeRequest(
    val phone: String,
    val code: String
)