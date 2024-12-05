package uz.isheraliyev.feature.profile.presentation.screen.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import uz.isheraliyev.core.presenter.component.button.WinDiButton
import uz.isheraliyev.core.presenter.design.theme.WinDiTheme
import uz.isheraliyev.feature.profile.R
import uz.isheraliyev.feature.profile.presentation.component.card.UserProfileCard
import uz.isheraliyev.feature.profile.presentation.component.item.ProfileInfoItem

@Composable
fun ProfileScreen(
    uiState: ProfileUIState,
    onIntent: (ProfileIntent) -> Unit
) {
    Scaffold(
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {

                if (uiState.userDate != null) UserProfileCard(
                    userData = uiState.userDate,
                    enabled = true,
                    onClick = {},
                    onCameraClick = {}
                )

                Spacer(modifier = Modifier.height(16.dp))

                if (uiState.userDate != null) LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.background(
                        color = WinDiTheme.color.secondary,
                        shape = RoundedCornerShape(12.dp)
                    )
                ) {
                    item {
                        ProfileInfoItem(
                            title = stringResource(R.string.number),
                            desc = uiState.userDate.profileData.phone,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }

                    item { HorizontalDivider(color = WinDiTheme.color.border) }

                    item {
                        ProfileInfoItem(
                            title = stringResource(R.string.living_city),
                            desc = uiState.userDate.profileData.city,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }

                    item { HorizontalDivider(color = WinDiTheme.color.border) }

                    item {
                        ProfileInfoItem(
                            title = stringResource(R.string.date_of_birth),
                            desc = uiState.userDate.profileData.birthday,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }

                    item { HorizontalDivider(color = WinDiTheme.color.border) }

                    item {
                        ProfileInfoItem(
                            title = stringResource(R.string.status),
                            desc = uiState.userDate.profileData.status,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                if (uiState.userDate != null) WinDiButton(
                    text = stringResource(R.string.update),
                    containerColor = WinDiTheme.color.white,
                    contentColor = WinDiTheme.color.black,
                    modifier = Modifier.fillMaxWidth(),
                    border = BorderStroke(width = 1.dp, color = WinDiTheme.color.black),
                    contentPaddingValues = PaddingValues(16.dp),
                    onClick = { onIntent(ProfileIntent.OnUpdateProfile) }
                )
            }
        }
    )
}