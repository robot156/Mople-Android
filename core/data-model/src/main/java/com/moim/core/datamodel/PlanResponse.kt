package com.moim.core.datamodel

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlanResponse(
    @SerialName("creatorId")
    val userId: String = "",
    @SerialName("planId")
    val planId: String = "",
    @SerialName("meetId")
    val meetingId: String = "",
    @SerialName("meetName")
    val meetingName: String = "",
    @SerialName("meetImg")
    val meetingImage: String = "",
    @SerialName("planName")
    val planName: String = "",
    @SerialName("planMemberCount")
    val planMemberCount: Int = 1,
    @SerialName("planTime")
    val planTime: String = "",
    @SerialName("planAddress")
    val planAddress: String = "",
    @SerialName("title")
    val placeName: String = "",
    @SerialName("lot")
    val planLongitude: Double = 0.0,
    @SerialName("lat")
    val planLatitude: Double = 0.0,
    @SerialName("weatherAddress")
    val weatherAddress: String = "",
    @SerialName("weatherIcon")
    val weatherIconUrl: String = "",
    @SerialName("temperature")
    val temperature: Float = 0f,
    @SerialName("participant")
    val isParticipant: Boolean = false,
)