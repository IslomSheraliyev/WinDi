package uz.isheraliyev.feature.register.data.di

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import uz.isheraliyev.feature.register.data.repository.RegisterRepositoryImpl
import uz.isheraliyev.feature.register.data.service.RegisterApiService
import uz.isheraliyev.feature.register.domain.repository.RegisterRepository
import uz.isheraliyev.feature.register.domain.usecase.RegisterUseCase

object Register {
    val module = module {
        singleOf(::RegisterApiService)
        singleOf(::RegisterRepositoryImpl) { bind<RegisterRepository>() }
        singleOf(::RegisterUseCase)
    }
}