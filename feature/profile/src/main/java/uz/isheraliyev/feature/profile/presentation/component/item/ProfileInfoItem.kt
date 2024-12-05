package uz.isheraliyev.feature.profile.presentation.component.item

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import uz.isheraliyev.core.presenter.design.theme.WinDiTheme

@Composable
fun ProfileInfoItem(
    title: String,
    desc: String,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(0.dp),
        colors = CardDefaults.cardColors(WinDiTheme.color.secondary)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(
                text = title,
                color = WinDiTheme.color.black,
                style = WinDiTheme.font.bodyMediumMedium
            )

            Text(
                text = desc,
                color = WinDiTheme.color.grey,
                style = WinDiTheme.font.captionSmallRegular
            )
        }
    }
}