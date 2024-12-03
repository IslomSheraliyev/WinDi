package uz.isheraliyev.feature.auth.presenter.screen.credentials

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import uz.isheraliyev.core.presenter.component.button.WinDiButton
import uz.isheraliyev.core.presenter.design.theme.WinDiTheme
import uz.isheraliyev.feature.auth.R
import uz.isheraliyev.feature.auth.presenter.component.input.AuthCountryCodeTextField
import uz.isheraliyev.feature.auth.presenter.component.input.AuthNumberTextField

@Composable
fun CredentialsScreen(
    uiState: CredentialsUIState,
    onIntent: (CredentialsIntent) -> Unit
) {
    Scaffold(
        containerColor = WinDiTheme.color.white,
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .background(WinDiTheme.color.white)
                    .padding(top = 44.dp)
                    .padding(16.dp)
            ) {
                Text(
                    text = stringResource(R.string.auth_enter_number),
                    color = WinDiTheme.color.black,
                    style = WinDiTheme.font.headingLargeSemiBold
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = stringResource(R.string.auth_enter_number_description),
                    color = WinDiTheme.color.grey,
                    style = WinDiTheme.font.bodyMediumRegular
                )

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.height(IntrinsicSize.Min)
                ) {
                    AuthCountryCodeTextField(
                        countryCode = uiState.countryCode,
                        onCodeChange = { onIntent(CredentialsIntent.OnChangeCountryCode(it)) }
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    AuthNumberTextField(
                        number = uiState.number,
                        placeholder = stringResource(R.string.auth_number),
                        modifier = Modifier.weight(1f),
                        onNumberChange = { onIntent(CredentialsIntent.OnChangeNumber(it)) }
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                WinDiButton(
                    text = stringResource(R.string.auth_login),
                    enabled = uiState.buttonState,
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { onIntent(CredentialsIntent.OnLogin) }
                )
            }
        }
    )
}