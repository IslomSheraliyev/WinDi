package uz.isheraliyev.windi.app

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import uz.isheraliyev.core.data.di.Network
import uz.isheraliyev.core.data.local.AppPreferences
import uz.isheraliyev.feature.auth.data.di.Auth
import uz.isheraliyev.feature.auth.presenter.di.AuthViewModel

class WinDiApp : Application() {
    override fun onCreate() {
        super.onCreate()
        AppPreferences.init(this)

        startKoin {
            androidContext(this@WinDiApp)
            modules(
                Network.module,
                Auth.module,
                AuthViewModel.module
            )
        }
    }
}