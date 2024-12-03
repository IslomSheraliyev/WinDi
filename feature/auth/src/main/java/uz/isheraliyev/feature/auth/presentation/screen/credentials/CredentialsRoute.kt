package uz.isheraliyev.feature.auth.presentation.screen.credentials

import androidx.compose.runtime.*
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel
import uz.isheraliyev.core.presenter.dialog.LoadingDialog

@Composable
fun CredentialsRoute(
    onNext: (String) -> Unit,
    viewModel: CredentialsViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    var loading by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.actionState.collectLatest { action ->
            when (action) {
                is CredentialsActionState.Error -> loading = false
                is CredentialsActionState.Loading -> loading = true
                is CredentialsActionState.Success -> onNext(action.number)
            }
        }
    }

    CredentialsScreen(
        uiState = uiState,
        onIntent = { intent ->
            when (intent) {
                is CredentialsIntent.OnChangeNumber -> viewModel.setNumber(intent.number)
                is CredentialsIntent.OnLogin -> viewModel.sendAuthCode()
                is CredentialsIntent.OnChangeCountryCode -> viewModel.setCountryCode(intent.countryCode)
            }
        }
    )

    LoadingDialog(isVisible = loading)
}