package uz.isheraliyev.windi.app

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import uz.isheraliyev.windi.MainViewModel

object App {
    val module = module {
        viewModelOf(::MainViewModel)
    }
}