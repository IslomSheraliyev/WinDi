package uz.isheraliyev.feature.auth.presenter.di

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import uz.isheraliyev.feature.auth.presenter.screen.credentials.CredentialsViewModel
import uz.isheraliyev.feature.auth.presenter.screen.verification.VerificationViewModel

object AuthViewModel {
    val module = module {
        viewModelOf(::CredentialsViewModel)
        viewModelOf(::VerificationViewModel)
    }
}