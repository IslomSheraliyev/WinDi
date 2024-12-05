import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import org.threeten.bp.LocalDate
import uz.ildam.technologies.yalla.android.ui.components.date_picker.WheelDatePicker
import uz.isheraliyev.core.presenter.component.button.WinDiButton
import uz.isheraliyev.core.presenter.design.theme.WinDiTheme
import uz.isheraliyev.feature.profile.R
import uz.isheraliyev.feature.profile.presentation.component.date_picker.WheelPickerDefaults
import uz.isheraliyev.feature.profile.presentation.utils.formatWithDashesDMY

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerBottomSheet(
    startDate: LocalDate,
    onSelectDate: (LocalDate) -> Unit,
    sheetState: SheetState,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier
) {
    var snappedDate by remember { mutableStateOf(startDate) }

    ModalBottomSheet(
        dragHandle = null,
        sheetState = sheetState,
        onDismissRequest = onDismissRequest,
        shape = RoundedCornerShape(30.dp)
    ) {

        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = modifier.navigationBarsPadding()
        ) {
            Card(
                colors = CardDefaults.cardColors(WinDiTheme.color.white),
                shape = RoundedCornerShape(30.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.choose_date),
                        color = WinDiTheme.color.black,
                        style = WinDiTheme.font.headingLargeSemiBold
                    )

                    Text(
                        text = snappedDate.formatWithDashesDMY(),
                        color = WinDiTheme.color.grey,
                        style = WinDiTheme.font.bodySmallRegular
                    )
                }
            }

            Card(
                shape = RoundedCornerShape(30.dp),
                colors = CardDefaults.cardColors(WinDiTheme.color.white)
            ) {
                WheelDatePicker(
                    rowCount = 5,
                    textColor = WinDiTheme.color.black,
                    textStyle = WinDiTheme.font.bodyMediumMedium,
                    onSnappedDate = { snappedDate = it },
                    maxDate = LocalDate.now(),
                    size = DpSize(width = 192.dp, height = 170.dp),
                    yearsRange = IntRange(1900, 2024),
                    startDate = snappedDate,
                    selectorProperties = WheelPickerDefaults.selectorProperties(false),
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(WinDiTheme.color.white)
                )
            }

            Card(
                colors = CardDefaults.cardColors(WinDiTheme.color.white),
                shape = RoundedCornerShape(30.dp)
            ) {
                WinDiButton(
                    text = stringResource(id = R.string.choose_date),
                    contentPaddingValues = PaddingValues(16.dp),
                    onClick = {
                        onSelectDate(snappedDate)
                        onDismissRequest()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                )
            }
        }
    }
}