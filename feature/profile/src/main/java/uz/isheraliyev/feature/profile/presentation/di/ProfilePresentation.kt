package uz.isheraliyev.feature.profile.presentation.di

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import uz.isheraliyev.feature.profile.presentation.screen.ProfileViewModel

object ProfilePresentation {
    val module = module {
        viewModelOf(::ProfileViewModel)
    }
}