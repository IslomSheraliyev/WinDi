package uz.isheraliyev.feature.auth.presenter.screen.verification

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import uz.isheraliyev.core.presenter.design.theme.WinDiTheme
import uz.isheraliyev.feature.auth.R
import uz.isheraliyev.feature.auth.presenter.component.otp.OtpView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VerificationScreen(
    uiState: VerificationUIState,
    expiresIn: Int,
    snackbarHostState: SnackbarHostState,
    onIntent: (VerificationIntent) -> Unit
) {
    Scaffold(
        containerColor = WinDiTheme.color.white,
        topBar = {
            TopAppBar(
                title = {},
                colors = TopAppBarDefaults.topAppBarColors(WinDiTheme.color.white),
                navigationIcon = {
                    IconButton(
                        onClick = { onIntent(VerificationIntent.OnNavigateBack) }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {
                Text(
                    text = stringResource(R.string.auth_enter_code),
                    color = WinDiTheme.color.black,
                    style = WinDiTheme.font.headingLargeSemiBold
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = stringResource(R.string.auth_enter_code_description),
                    color = WinDiTheme.color.grey,
                    style = WinDiTheme.font.bodyMediumRegular
                )

                Spacer(modifier = Modifier.height(56.dp))

                OtpView(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    otpText = uiState.otp,
                    isError = uiState.isError,
                    otpCount = 6,
                    containerColor = WinDiTheme.color.border,
                    containerHeight = 56.dp,
                    containerWidth = 38.dp,
                    selectedContainerColor = WinDiTheme.color.black,
                    onOtpTextChange = { onIntent(VerificationIntent.OnOtpChange(it)) }
                )

                Spacer(modifier = Modifier.height(24.dp))

                TextButton(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    enabled = expiresIn == 0,
                    contentPadding = PaddingValues(horizontal = 32.dp, vertical = 8.dp),
                    onClick = { onIntent(VerificationIntent.ResendAuthCode) }
                ) {
                    if (expiresIn > 0) Text(
                        text = stringResource(R.string.auth_resend_in, expiresIn),
                        color = WinDiTheme.color.grey,
                        style = WinDiTheme.font.bodyMediumMedium,
                        textAlign = TextAlign.Center
                    )
                    else Text(
                        text = stringResource(R.string.auth_resend),
                        color = WinDiTheme.color.black,
                        style = WinDiTheme.font.bodyMediumMedium,
                        textAlign = TextAlign.Center
                    )
                }
            }
        },
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier.imePadding()
            )
        }
    )
}