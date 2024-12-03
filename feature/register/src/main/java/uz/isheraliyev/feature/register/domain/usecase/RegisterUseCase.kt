package uz.isheraliyev.feature.register.domain.usecase

import uz.isheraliyev.feature.register.domain.repository.RegisterRepository

class RegisterUseCase(
    private val repository: RegisterRepository
) {
    suspend operator fun invoke(phone: String, name: String, username: String) =
        repository.register(
            phone = phone,
            name = name,
            username = username
        )
}