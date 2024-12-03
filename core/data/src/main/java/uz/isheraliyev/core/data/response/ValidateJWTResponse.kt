package uz.isheraliyev.core.data.response

import kotlinx.serialization.Serializable

@Serializable
data class ValidateJWTResponse(
    val errors: Boolean?,
    val is_valid: Boolean?
)