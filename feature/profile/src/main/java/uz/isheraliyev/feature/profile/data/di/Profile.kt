package uz.isheraliyev.feature.profile.data.di

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import uz.isheraliyev.feature.profile.data.repository.ProfileRepositoryImpl
import uz.isheraliyev.feature.profile.data.service.ProfileApiService
import uz.isheraliyev.feature.profile.domain.repository.ProfileRepository
import uz.isheraliyev.feature.profile.domain.usecase.GetMeUseCase
import uz.isheraliyev.feature.profile.domain.usecase.PostMeUseCase

object Profile {
    val module = module {
        singleOf(::ProfileApiService)
        singleOf(::ProfileRepositoryImpl) { bind<ProfileRepository>() }
        singleOf(::GetMeUseCase)
        singleOf(::PostMeUseCase)
    }
}