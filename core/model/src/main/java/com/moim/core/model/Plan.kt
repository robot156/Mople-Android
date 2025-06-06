package com.moim.core.model

import android.os.Bundle
import androidx.compose.runtime.Stable
import androidx.navigation.NavType
import com.moim.core.datamodel.PlanResponse
import com.moim.core.model.util.encoding
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Stable
@Serializable
data class Plan(
    val userId: String = "",
    val meetingId: String = "",
    val meetingName: String = "",
    val meetingImageUrl: String = "",
    val planId: String = "",
    val planName: String = "",
    val planMemberCount: Int = 0,
    val planTime: String = "",
    val planAddress: String = "",
    val planLongitude: Double = 0.0,
    val planLatitude: Double = 0.0,
    val placeName: String = "",
    val weatherAddress: String = "",
    val weatherIconUrl: String = "",
    val temperature: Float = 0f,
    val isParticipant: Boolean = true,
)

fun PlanResponse.asItem(): Plan {
    return Plan(
        userId = userId,
        meetingId = meetingId,
        meetingName = meetingName,
        meetingImageUrl = meetingImage,
        planId = planId,
        planName = planName,
        planMemberCount = planMemberCount,
        planTime = planTime,
        planLatitude = planLatitude,
        planLongitude = planLongitude,
        planAddress = planAddress,
        placeName = placeName,
        weatherAddress = weatherAddress,
        weatherIconUrl = weatherIconUrl,
        temperature = temperature,
        isParticipant = isParticipant
    )
}

val PlanType = object : NavType<Plan?>(isNullableAllowed = true) {
    override fun get(bundle: Bundle, key: String): Plan? {
        return bundle.getString(key)?.let { Json.decodeFromString(it) }
    }

    override fun parseValue(value: String): Plan? {
        return Json.decodeFromString(value)
    }

    override fun put(bundle: Bundle, key: String, value: Plan?) {
        if (value != null) bundle.putString(key, Json.encodeToString(Plan.serializer(), value))
    }

    override fun serializeAsValue(value: Plan?): String {
        return value
            ?.let {
                Json.encodeToString(
                    serializer = Plan.serializer(),
                    value = it.copy(
                        meetingImageUrl = it.meetingImageUrl.encoding(),
                        weatherIconUrl = it.weatherIconUrl.encoding()
                    )
                )
            }
            ?: ""
    }
}