package uz.isheraliyev.feature.auth.presenter.screen.verification

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import uz.isheraliyev.feature.auth.R

@Composable
fun VerificationRoute(
    number: String,
    onNavigateBack: () -> Unit,
    onUserFound: () -> Unit,
    onUserNotFound: () -> Unit,
    viewModel: VerificationViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    var loading by remember { mutableStateOf(false) }
    var expiresIn by remember { mutableIntStateOf(60) }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val serverErrorMessage = stringResource(R.string.server_error)
    val succeedMessage = stringResource(R.string.succeed)
    val wrongCodeMessage = stringResource(R.string.auth_wrong_code)

    LaunchedEffect(Unit) {
        viewModel.setNumber(number)

        launch {
            viewModel.countDownTimer(60).collectLatest { expiresIn = it }
        }

        launch {
            viewModel.actionState.collectLatest { action ->
                when (action) {
                    is VerificationActionState.Loading -> loading = true
                    is VerificationActionState.ResendError -> scope.launch {
                        snackbarHostState.showSnackbar(
                            message = serverErrorMessage,
                            withDismissAction = true,
                            duration = SnackbarDuration.Short
                        )
                    }

                    is VerificationActionState.ResendSuccess -> scope.launch {
                        snackbarHostState.showSnackbar(
                            message = succeedMessage,
                            withDismissAction = true,
                            duration = SnackbarDuration.Short
                        )

                        viewModel.countDownTimer(60).collectLatest { expiresIn = it }
                    }

                    is VerificationActionState.VerificationError -> scope.launch {
                        snackbarHostState.showSnackbar(
                            message = wrongCodeMessage,
                            withDismissAction = true,
                            duration = SnackbarDuration.Short
                        )
                    }

                    is VerificationActionState.VerificationSuccess -> scope.launch {
                        snackbarHostState.showSnackbar(
                            message = succeedMessage,
                            withDismissAction = true,
                            duration = SnackbarDuration.Short
                        )

                        if (action.isUserExists) onUserFound()
                        else onUserNotFound()
                    }
                }
            }
        }
    }

    VerificationScreen(
        uiState = uiState,
        expiresIn = expiresIn,
        snackbarHostState = snackbarHostState,
        onIntent = { intent ->
            when (intent) {
                is VerificationIntent.OnNavigateBack -> onNavigateBack()
                is VerificationIntent.OnOtpChange -> viewModel.setOtp(intent.otp)
                is VerificationIntent.ResendAuthCode -> viewModel.resendAuthCode()
            }
        }
    )
}