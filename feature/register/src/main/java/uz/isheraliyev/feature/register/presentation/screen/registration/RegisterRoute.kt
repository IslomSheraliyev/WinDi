package uz.isheraliyev.feature.register.presentation.screen.registration

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
fun RegisterRoute(
    number: String,
    onRegister: () -> Unit,
    viewModel: RegisterViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    var loading by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.setNumber(number)

        launch {
            viewModel.actionState.collectLatest { action ->
                when (action) {
                    RegisterActionState.Loading -> loading = true
                    RegisterActionState.Error -> loading = false
                    RegisterActionState.Success -> onRegister()
                }
            }
        }
    }

    RegisterScreen(
        uiState = uiState,
        onIntent = { intent ->
            when (intent) {
                is RegisterIntent.OnChangeName -> viewModel.setName(intent.name)
                is RegisterIntent.OnChangeUsername -> viewModel.setUsername(intent.username)
                is RegisterIntent.Register -> viewModel.register()
            }
        }
    )

    LoadingDialog(isVisible = loading)
}