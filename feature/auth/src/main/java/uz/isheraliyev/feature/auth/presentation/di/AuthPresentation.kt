package uz.isheraliyev.feature.auth.presentation.di

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import uz.isheraliyev.feature.auth.presentation.screen.credentials.CredentialsViewModel
import uz.isheraliyev.feature.auth.presentation.screen.verification.VerificationViewModel

object AuthPresentation {
    val module = module {
        viewModelOf(::CredentialsViewModel)
        viewModelOf(::VerificationViewModel)
    }
}