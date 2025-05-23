package com.moim.core.designsystem.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moim.core.designsystem.R
import com.moim.core.designsystem.component.MoimPrimaryButton
import com.moim.core.designsystem.component.MoimText
import com.moim.core.designsystem.component.containerScreen
import com.moim.core.designsystem.theme.MoimTheme

@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier,
    onClickRefresh: () -> Unit,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(24.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        MoimText(
            text = stringResource(id = R.string.common_error),
            style = MoimTheme.typography.body01.medium,
            color = MoimTheme.colors.gray.gray01,
        )

        MoimText(
            text = stringResource(id = R.string.common_error_disconnection),
            textAlign = TextAlign.Center,
            style = MoimTheme.typography.body01.semiBold,
            singleLine = false,
            color = MoimTheme.colors.gray.gray01,
        )

        MoimPrimaryButton(
            onClick = onClickRefresh,
            verticalPadding = 8.dp,
            text = stringResource(R.string.common_refresh)
        )
    }
}

@Preview
@Composable
private fun ErrorScreenPreview() {
    MoimTheme {
        ErrorScreen(
            modifier = Modifier.containerScreen(),
            onClickRefresh = {}
        )
    }
}