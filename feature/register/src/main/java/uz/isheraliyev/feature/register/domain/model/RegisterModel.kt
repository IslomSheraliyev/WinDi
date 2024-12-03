package uz.isheraliyev.feature.register.domain.model

data class RegisterModel(
    val refreshToken: String,
    val accessToken: String,
    val userId: Int
)