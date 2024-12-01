package uz.isheraliyev.feature.auth.domain.model

data class CheckAuthCodeModel(
    val refreshToken: String,
    val accessToken: String,
    val userId: Int,
    val isUserExists: Boolean
)