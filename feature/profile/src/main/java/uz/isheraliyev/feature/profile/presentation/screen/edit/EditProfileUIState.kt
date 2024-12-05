package uz.isheraliyev.feature.profile.presentation.screen.edit

import org.threeten.bp.LocalDate

data class EditProfileUIState(
    val name: String = "",
    val username: String = "",
    val status: String = "",
    val livingLocation: String = "",
    val dateOfBirth: LocalDate = LocalDate.now(),
    val filename: String = "",
    val base64: String = ""
)