package uz.isheraliyev.core.data.local

import androidx.navigation.NavController

class NavigationManager {
    private var navController: NavController? = null

    fun setNavController(controller: NavController) {
        this.navController = controller
    }

    fun navigate(route: String) {
        navController?.navigate(route)
    }

    fun popBackStack() {
        navController?.popBackStack()
    }
}