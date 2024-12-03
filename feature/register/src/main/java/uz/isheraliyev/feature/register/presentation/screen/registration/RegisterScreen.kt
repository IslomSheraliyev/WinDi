package uz.isheraliyev.feature.register.presentation.screen.registration

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import uz.isheraliyev.core.presenter.component.button.WinDiButton
import uz.isheraliyev.core.presenter.design.theme.WinDiTheme
import uz.isheraliyev.feature.register.presentation.component.input.NameTextField
import uz.isheraliyev.feature.registration.R

@Composable
fun RegisterScreen(
    uiState: RegisterUIState,
    onIntent: (RegisterIntent) -> Unit
) {
    Scaffold(
        containerColor = WinDiTheme.color.white,
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(top = 32.dp)
                    .padding(16.dp)
            ) {
                Text(
                    text = stringResource(R.string.registration),
                    color = WinDiTheme.color.black,
                    style = WinDiTheme.font.headingLargeSemiBold
                )

                Spacer(modifier = Modifier.height(24.dp))

                HorizontalDivider(color = WinDiTheme.color.border)

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = stringResource(R.string.registration_name),
                    color = WinDiTheme.color.black,
                    style = WinDiTheme.font.captionSmallRegular
                )

                Spacer(modifier = Modifier.height(8.dp))

                NameTextField(
                    name = uiState.name,
                    modifier = Modifier.fillMaxWidth(),
                    onNameChange = { onIntent(RegisterIntent.OnChangeName(it)) }
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = stringResource(R.string.registration_username),
                    color = WinDiTheme.color.black,
                    style = WinDiTheme.font.captionSmallRegular
                )

                Spacer(modifier = Modifier.height(8.dp))

                NameTextField(
                    name = uiState.username,
                    modifier = Modifier.fillMaxWidth(),
                    onNameChange = { onIntent(RegisterIntent.OnChangeUsername(it)) }
                )

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    text = stringResource(R.string.registration_a_to_z_uppercase_rule),
                    color = WinDiTheme.color.grey,
                    style = WinDiTheme.font.captionSmallRegular
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = stringResource(R.string.registration_a_to_z_lowercase_rule),
                    color = WinDiTheme.color.grey,
                    style = WinDiTheme.font.captionSmallRegular
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = stringResource(R.string.registration_number_0_to_9_rule),
                    color = WinDiTheme.color.grey,
                    style = WinDiTheme.font.captionSmallRegular
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = stringResource(R.string.registration_symbol_rule),
                    color = WinDiTheme.color.grey,
                    style = WinDiTheme.font.captionSmallRegular
                )

                Spacer(modifier = Modifier.height(24.dp))

                WinDiButton(
                    text = stringResource(R.string.registration_next),
                    modifier = Modifier.fillMaxWidth(),
                    enabled = uiState.buttonState,
                    onClick = { onIntent(RegisterIntent.Register) }
                )
            }
        }
    )
}