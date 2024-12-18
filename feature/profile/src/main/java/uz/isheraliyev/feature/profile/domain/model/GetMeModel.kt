package uz.isheraliyev.feature.profile.domain.model

data class GetMeModel(
    val profileData: ProfileData
) {
    data class ProfileData(
        val name: String,
        val username: String,
        val birthday: String,
        val city: String,
        val vk: String,
        val instagram: String,
        val status: String,
        val avatar: String,
        val id: Int,
        val last: String,
        val online: Boolean,
        val created: String,
        val phone: String,
        val completedTask: Int,
        val avatars: Avatars
    ) {
        data class Avatars(
            val avatar: String,
            val bigAvatar: String,
            val miniAvatar: String
        )
    }
}