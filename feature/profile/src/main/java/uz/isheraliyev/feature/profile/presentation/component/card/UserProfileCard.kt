package uz.isheraliyev.feature.profile.presentation.component.card

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import uz.isheraliyev.core.data.local.Constants.DOMAIN
import uz.isheraliyev.core.presenter.design.theme.WinDiTheme
import uz.isheraliyev.feature.profile.domain.model.GetMeModel

@Composable
fun UserProfileCard(
    userData: GetMeModel,
    modifier: Modifier = Modifier,
    enabled: Boolean,
    onCameraClick: () -> Unit,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        enabled = enabled,
        onClick = onClick,
        colors = CardDefaults.cardColors(
            containerColor = WinDiTheme.color.secondary,
            disabledContainerColor = WinDiTheme.color.secondary
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(16.dp)
                .height(IntrinsicSize.Min)
        ) {
            AsyncImage(
                model = "$DOMAIN${userData.profileData.avatars.miniAvatar}",
                contentDescription = null,

                modifier = Modifier
                    .size(52.dp)
                    .clip(CircleShape)
                    .border(
                        width = 1.dp,
                        color = WinDiTheme.color.black,
                        shape = CircleShape
                    )
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier
                    .padding(vertical = 4.dp)
                    .weight(1f)
            ) {
                Text(
                    text = userData.profileData.name,
                    color = WinDiTheme.color.black,
                    style = WinDiTheme.font.bodyMediumRegular
                )

                Text(
                    text = userData.profileData.username,
                    color = WinDiTheme.color.grey,
                    style = WinDiTheme.font.bodyMediumRegular
                )
            }

            IconButton(
                onClick = onCameraClick,
                colors = IconButtonDefaults.iconButtonColors(WinDiTheme.color.grey)
            ) {
                Icon(
                    imageVector = Icons.Default.PhotoCamera,
                    contentDescription = null,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}