package uz.isheraliyev.feature.profile.presentation.screen.profile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import uz.isheraliyev.core.presenter.dialog.LoadingDialog

@Composable
fun ProfileRoute(
    onNavigateBack: () -> Unit,
    onUpdateProfile: () -> Unit,
    viewModel: ProfileViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    var loading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        launch {
            if (uiState.userDate == null) viewModel.getMe()
            viewModel.actionState.collectLatest { action ->
                loading = when (action) {
                    ProfileActionState.Error -> false
                    ProfileActionState.Loading -> true
                    ProfileActionState.Success -> false
                }
            }
        }
    }

    ProfileScreen(
        uiState = uiState,
        onIntent = { intent ->
            when (intent) {
                ProfileIntent.OnNavigateBack -> onNavigateBack()
                ProfileIntent.OnUpdateProfile -> onUpdateProfile()
            }
        }
    )

    LoadingDialog(isVisible = loading && uiState.userDate == null)
}