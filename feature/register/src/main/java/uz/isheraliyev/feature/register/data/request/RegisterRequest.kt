package uz.isheraliyev.feature.register.data.request

import kotlinx.serialization.Serializable

@Serializable
data class RegisterRequest(
    val phone: String,
    val name: String,
    val username: String
)
