package uz.isheraliyev.feature.profile.data.request

import kotlinx.serialization.Serializable

@Serializable
data class PostMeRequest(
    val name: String,
    val username: String,
    val birthday: String,
    val city: String,
    val status: String,
    val avatar: Avatar
) {
    @Serializable
    data class Avatar(
        val filename: String,
        val base_64: String
    )
}