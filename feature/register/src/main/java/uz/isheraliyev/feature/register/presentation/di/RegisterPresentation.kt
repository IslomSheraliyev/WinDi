package uz.isheraliyev.feature.register.presentation.di

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import uz.isheraliyev.feature.register.presentation.screen.registration.RegisterViewModel

object RegisterPresentation {
    val module = module {
        viewModelOf(::RegisterViewModel)
    }
}