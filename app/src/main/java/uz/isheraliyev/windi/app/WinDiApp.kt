package uz.isheraliyev.windi.app

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import uz.isheraliyev.core.data.di.Network
import uz.isheraliyev.core.data.local.AppPreferences
import uz.isheraliyev.feature.auth.data.di.Auth
import uz.isheraliyev.feature.auth.presentation.di.AuthPresentation
import uz.isheraliyev.feature.profile.data.di.Profile
import uz.isheraliyev.feature.profile.presentation.di.ProfilePresentation
import uz.isheraliyev.feature.register.data.di.Register
import uz.isheraliyev.feature.register.presentation.di.RegisterPresentation

class WinDiApp : Application() {
    override fun onCreate() {
        super.onCreate()
        AppPreferences.init(this)
        AndroidThreeTen.init(this)

        startKoin {
            androidContext(this@WinDiApp)
            modules(
//                App.module,
                Network.module,
                Auth.module,
                AuthPresentation.module,
                Register.module,
                RegisterPresentation.module,
                Profile.module,
                ProfilePresentation.module
            )
        }
    }
}