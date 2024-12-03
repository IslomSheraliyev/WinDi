package uz.isheraliyev.feature.auth.data.di

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import uz.isheraliyev.feature.auth.data.repository.AuthRepositoryImpl
import uz.isheraliyev.feature.auth.data.service.AuthApiService
import uz.isheraliyev.feature.auth.domain.repository.AuthRepository
import uz.isheraliyev.feature.auth.domain.usecase.CheckAuthCodeUseCase
import uz.isheraliyev.feature.auth.domain.usecase.SendAuthCodeUseCase

object Auth {
    val module = module {
        singleOf(::AuthApiService)
        singleOf(::AuthRepositoryImpl) { bind<AuthRepository>() }
        singleOf(::SendAuthCodeUseCase)
        singleOf(::CheckAuthCodeUseCase)
    }
}