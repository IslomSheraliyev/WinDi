package uz.isheraliyev.feature.profile.presentation.screen.edit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import uz.isheraliyev.core.presenter.component.button.WinDiButton
import uz.isheraliyev.core.presenter.design.theme.WinDiTheme
import uz.isheraliyev.feature.profile.R
import uz.isheraliyev.feature.profile.presentation.component.item.ProfileEditInfoItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(
    uiState: EditProfileUIState,
    onIntent: (EditProfileIntent) -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(WinDiTheme.color.white),
                navigationIcon = {
                    IconButton(onClick = { onIntent(EditProfileIntent.OnNavigateBack) }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = null
                        )
                    }
                },
                title = {
                    Text(
                        text = stringResource(R.string.edit_profile),
                        color = WinDiTheme.color.black,
                        style = WinDiTheme.font.bodyMediumSemiBold,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            )
        },
        containerColor = WinDiTheme.color.white,
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .systemBarsPadding()
            ) {
                LazyColumn(
                    modifier = Modifier.padding(paddingValues),
                    contentPadding = PaddingValues(bottom = 48.dp)
                ) {
                    item {
                        ProfileEditInfoItem(
                            value = uiState.name,
                            title = stringResource(R.string.name),
                            onValueChange = { onIntent(EditProfileIntent.SetName(it)) }
                        )
                    }

                    item { Spacer(modifier = Modifier.height(8.dp)) }

                    item {
                        ProfileEditInfoItem(
                            value = uiState.username,
                            title = stringResource(R.string.username),
                            onValueChange = { onIntent(EditProfileIntent.SetUsername(it)) }
                        )
                    }

                    item { Spacer(modifier = Modifier.height(8.dp)) }

                    item {
                        ProfileEditInfoItem(
                            value = uiState.livingLocation,
                            title = stringResource(R.string.living_city),
                            onValueChange = { onIntent(EditProfileIntent.SetLivingLocation(it)) }
                        )
                    }

                    item { Spacer(modifier = Modifier.height(8.dp)) }

                    item {
                        Card(
                            shape = RectangleShape,
                            colors = CardDefaults.cardColors(WinDiTheme.color.secondary),
                            onClick = { onIntent(EditProfileIntent.OnClickDateOfBirth) },
                        ) {
                            ProfileEditInfoItem(
                                value = uiState.dateOfBirth.toString(),
                                enabled = false,
                                title = stringResource(R.string.date_of_birth),
                                onValueChange = { }
                            )
                        }
                    }

                    item { Spacer(modifier = Modifier.height(8.dp)) }

                    item {
                        ProfileEditInfoItem(
                            value = uiState.status,
                            title = stringResource(R.string.status),
                            onValueChange = { onIntent(EditProfileIntent.SetStatus(it)) }
                        )
                    }
                }

                Spacer(modifier = Modifier.weight(1f))

                WinDiButton(
                    text = stringResource(R.string.save),
                    contentPaddingValues = PaddingValues(16.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    onClick = { onIntent(EditProfileIntent.OnSave) }
                )
            }
        }
    )
}