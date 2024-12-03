package uz.isheraliyev.feature.auth.presentation.screen.verification

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import uz.isheraliyev.core.presenter.dialog.LoadingDialog
import uz.isheraliyev.feature.auth.R

@Composable
fun VerificationRoute(
    number: String,
    onNavigateBack: () -> Unit,
    onUserFound: () -> Unit,
    onUserNotFound: (number: String) -> Unit,
    viewModel: VerificationViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    var loading by remember { mutableStateOf(false) }
    var expiresIn by remember { mutableIntStateOf(60) }
    val snackbarHostState = remember { SnackbarHostState() }

    val serverErrorMessage = stringResource(R.string.auth_server_error)
    val succeedMessage = stringResource(R.string.auth_succeed)
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
                    is VerificationActionState.ResendError -> {
                        loading = false
                        snackbarHostState.showSnackbar(
                            message = serverErrorMessage,
                            withDismissAction = true,
                            duration = SnackbarDuration.Short
                        )
                    }

                    is VerificationActionState.ResendSuccess -> {
                        loading = false

                        launch {
                            viewModel.countDownTimer(60).collectLatest {
                                expiresIn = it
                            }
                        }

                        launch {
                            snackbarHostState.showSnackbar(
                                message = succeedMessage,
                                withDismissAction = true,
                                duration = SnackbarDuration.Short
                            )
                        }
                    }

                    is VerificationActionState.VerificationError -> {
                        loading = false
                        snackbarHostState.showSnackbar(
                            message = wrongCodeMessage,
                            withDismissAction = true,
                            duration = SnackbarDuration.Short
                        )
                    }

                    is VerificationActionState.VerificationSuccess -> {
                        loading = false

                        launch {
                            snackbarHostState.showSnackbar(
                                message = succeedMessage,
                                withDismissAction = true,
                                duration = SnackbarDuration.Short
                            )
                        }

                        if (action.isUserExists) onUserFound()
                        else onUserNotFound(number)
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

    LoadingDialog(isVisible = loading)
}