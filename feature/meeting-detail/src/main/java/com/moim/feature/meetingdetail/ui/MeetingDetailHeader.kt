package com.moim.feature.meetingdetail.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.moim.core.designsystem.R
import com.moim.core.designsystem.component.MoimPrimaryButton
import com.moim.core.designsystem.component.MoimText
import com.moim.core.designsystem.component.NetworkImage
import com.moim.core.designsystem.component.onSingleClick
import com.moim.core.designsystem.theme.MoimTheme
import com.moim.core.designsystem.theme.moimButtomColors
import com.moim.core.model.Meeting
import com.moim.feature.meetingdetail.MeetingDetailUiAction
import com.moim.feature.meetingdetail.OnMeetingDetailUiAction

@Composable
fun MeetingDetailHeader(
    modifier: Modifier = Modifier,
    meeting: Meeting,
    isSelectedFuturePlan: Boolean = true,
    onUiAction: OnMeetingDetailUiAction = {}
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        MeetingDetailInfo(
            meeting = meeting,
            onUiAction = onUiAction
        )
        Spacer(Modifier.height(24.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MoimTheme.colors.bg.primary, shape = RoundedCornerShape(8.dp))
                .padding(6.dp)
        ) {
            MoimPrimaryButton(
                modifier = Modifier.weight(1f),
                buttonColors = moimButtomColors().copy(
                    containerColor = if (isSelectedFuturePlan) MoimTheme.colors.primary.primary else MoimTheme.colors.bg.primary,
                    contentColor = if (isSelectedFuturePlan) MoimTheme.colors.white else MoimTheme.colors.gray.gray04,
                ),
                text = stringResource(R.string.meeting_detail_future_plan),
                onClick = { onUiAction(MeetingDetailUiAction.OnClickPlanTab(true)) }
            )

            Spacer(Modifier.width(8.dp))

            MoimPrimaryButton(
                modifier = Modifier.weight(1f),
                buttonColors = moimButtomColors().copy(
                    containerColor = if (isSelectedFuturePlan) MoimTheme.colors.bg.primary else MoimTheme.colors.primary.primary,
                    contentColor = if (isSelectedFuturePlan) MoimTheme.colors.gray.gray04 else MoimTheme.colors.white,
                ),
                text = stringResource(R.string.meeting_detail_past_plan),
                onClick = { onUiAction(MeetingDetailUiAction.OnClickPlanTab(false)) }
            )
        }
    }
}

@Composable
private fun MeetingDetailInfo(
    modifier: Modifier = Modifier,
    meeting: Meeting,
    onUiAction: OnMeetingDetailUiAction,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        NetworkImage(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .border(BorderStroke(1.dp, MoimTheme.colors.stroke), shape = RoundedCornerShape(12.dp))
                .size(56.dp)
                .onSingleClick { onUiAction(MeetingDetailUiAction.OnClickMeetingImage(meeting.imageUrl, meeting.name)) },
            imageUrl = meeting.imageUrl,
            errorImage = painterResource(R.drawable.ic_empty_meeting)
        )

        Spacer(Modifier.width(12.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center
        ) {
            MoimText(
                modifier = Modifier.fillMaxWidth(),
                text = meeting.name,
                singleLine = false,
                style = MoimTheme.typography.title02.semiBold,
                color = MoimTheme.colors.gray.gray01
            )
            Spacer(Modifier.height(4.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.size(20.dp),
                    imageVector = ImageVector.vectorResource(R.drawable.ic_menu_meeting),
                    contentDescription = "",
                    tint = MoimTheme.colors.icon
                )
                Spacer(Modifier.width(4.dp))
                MoimText(
                    text = stringResource(R.string.unit_participants_count_short, meeting.memberCount),
                    style = MoimTheme.typography.body02.medium,
                    color = MoimTheme.colors.gray.gray04
                )

                MoimText(
                    modifier = Modifier.padding(horizontal = 4.dp),
                    text = stringResource(R.string.unit_dot),
                    style = MoimTheme.typography.body02.medium,
                    color = MoimTheme.colors.gray.gray04
                )

                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .onSingleClick { onUiAction(MeetingDetailUiAction.OnClickMeetingInvite) },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    MoimText(
                        text = stringResource(R.string.common_invite),
                        style = MoimTheme.typography.body02.medium,
                        color = MoimTheme.colors.gray.gray04
                    )

                    Icon(
                        modifier = Modifier.size(20.dp),
                        imageVector = ImageVector.vectorResource(R.drawable.ic_next),
                        contentDescription = "",
                        tint = MoimTheme.colors.icon
                    )
                }
            }
        }
    }
}