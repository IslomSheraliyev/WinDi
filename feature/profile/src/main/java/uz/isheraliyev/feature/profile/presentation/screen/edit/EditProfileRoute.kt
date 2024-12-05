package uz.isheraliyev.feature.profile.presentation.screen.edit

import DatePickerBottomSheet
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileRoute(
    onNavigateBack: () -> Unit,
    viewModel: EditProfileViewModel = koinViewModel()
) {
    var loading by remember { mutableStateOf(true) }
    val uiState by viewModel.uiState.collectAsState()
    var datePickerSheetVisibility by remember { mutableStateOf(false) }
    val datePickerSheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        launch { viewModel.getMe() }
        launch {
            viewModel.actionState.collectLatest { action ->
                loading = when (action) {
                    EditProfileActionState.Error -> false
                    EditProfileActionState.Loading -> true
                    EditProfileActionState.DataHasBeenFetched -> false
                    EditProfileActionState.DataHasBeenSaved -> {
                        onNavigateBack()
                        false
                    }

                }
            }
        }
    }

    EditProfileScreen(
        uiState = uiState,
        onIntent = { intent ->
            when (intent) {
                is EditProfileIntent.OnSave -> viewModel.postMe()
                is EditProfileIntent.OnClickDateOfBirth -> scope.launch {
                    datePickerSheetState.show()
                    datePickerSheetVisibility = true
                }

                is EditProfileIntent.SetLivingLocation -> viewModel.updateUIState(
                    EditProfileField.LivingLocation,
                    intent.location
                )

                is EditProfileIntent.SetName -> viewModel.updateUIState(
                    EditProfileField.Name,
                    intent.name
                )

                is EditProfileIntent.SetStatus -> viewModel.updateUIState(
                    EditProfileField.Status,
                    intent.status
                )

                is EditProfileIntent.SetUsername -> viewModel.updateUIState(
                    EditProfileField.Username,
                    intent.username
                )

                is EditProfileIntent.OnNavigateBack -> onNavigateBack()
            }
        }
    )

    if (datePickerSheetVisibility) DatePickerBottomSheet(
        sheetState = datePickerSheetState,
        startDate = uiState.dateOfBirth,
        onDismissRequest = {
            scope.launch {
                datePickerSheetState.hide()
                datePickerSheetVisibility = false
            }
        },
        onSelectDate = {
            viewModel.updateUIState(EditProfileField.DateOfBirth, it)
        }
    )
}