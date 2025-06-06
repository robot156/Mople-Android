package com.moim.feature.profileupdate.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.moim.core.designsystem.R
import com.moim.core.designsystem.component.MoimBottomSheetDialog
import com.moim.core.designsystem.component.MoimPrimaryButton
import com.moim.core.designsystem.component.MoimText
import com.moim.core.designsystem.theme.MoimTheme
import com.moim.core.designsystem.theme.moimButtomColors
import com.moim.feature.profileupdate.OnProfileUpdateUiAction
import com.moim.feature.profileupdate.ProfileUpdateUiAction
import kotlinx.coroutines.launch

@Composable
fun ProfileUpdateImageEditDialog(
    modifier: Modifier = Modifier,
    onUiAction: OnProfileUpdateUiAction
) {
    val dismissAction = ProfileUpdateUiAction.OnShowProfileEditDialog(false)
    val sheetState: SheetState = rememberModalBottomSheetState(true)
    val coroutineScope = rememberCoroutineScope()

    MoimBottomSheetDialog(
        modifier = modifier,
        onDismiss = {
            coroutineScope
                .launch { sheetState.hide() }
                .invokeOnCompletion { onUiAction(dismissAction) }
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MoimText(
                text = stringResource(R.string.common_profile_edit),
                singleLine = false,
                style = MoimTheme.typography.body01.semiBold,
                color = MoimTheme.colors.gray.gray01
            )
            Spacer(Modifier.height(24.dp))

            MoimPrimaryButton(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.common_default_select),
                buttonColors = moimButtomColors().copy(containerColor = MoimTheme.colors.gray.gray04),
                onClick = {
                    onUiAction(dismissAction)
                    onUiAction(ProfileUpdateUiAction.OnChangeProfileUrl(null))
                }
            )
            Spacer(Modifier.height(12.dp))

            MoimPrimaryButton(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.common_album_select),
                buttonColors = moimButtomColors().copy(containerColor = MoimTheme.colors.secondary),
                onClick = {
                    onUiAction(dismissAction)
                    onUiAction(ProfileUpdateUiAction.OnNavigatePhotoPicker)
                }
            )
            Spacer(Modifier.height(12.dp))
        }
    }
}