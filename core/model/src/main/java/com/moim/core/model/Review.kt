package com.moim.core.model

import android.os.Bundle
import androidx.compose.runtime.Stable
import androidx.navigation.NavType
import com.moim.core.datamodel.ReviewImageResponse
import com.moim.core.datamodel.ReviewResponse
import com.moim.core.model.util.encoding
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Stable
@Serializable
data class Review(
    val userId: String = "",
    val meetingId: String,
    val meetingName: String = "",
    val meetingImageUrl: String = "",
    val postId: String,
    val reviewId: String,
    val reviewName: String,
    val address: String,
    val latitude: Double = 0.0, // x
    val longitude: Double = 0.0, // y
    val placeName: String = "",
    val memberCount: Int = 1,
    val images: List<ReviewImage> = emptyList(),
    val reviewAt: String,
)

@Stable
@Serializable
data class ReviewImage(
    val imageId: String,
    val imageUrl: String,
)

fun ReviewResponse.asItem(): Review {
    return Review(
        userId = userId,
        meetingId = meetingId,
        meetingName = meetingName,
        meetingImageUrl = meetingImageUrl,
        postId = postId,
        reviewId = reviewId,
        reviewName = reviewName,
        address = address,
        latitude = latitude,
        longitude = longitude,
        placeName = placeName,
        memberCount = memberCount,
        images = images.map(ReviewImageResponse::asItem),
        reviewAt = reviewAt
    )
}

fun ReviewImageResponse.asItem(): ReviewImage {
    return ReviewImage(
        imageId = imageId,
        imageUrl = reviewImageUrl
    )
}

val ReviewType = object : NavType<Review?>(isNullableAllowed = true) {
    override fun get(bundle: Bundle, key: String): Review? {
        return bundle.getString(key)?.let { Json.decodeFromString(it) }
    }

    override fun parseValue(value: String): Review? {
        return Json.decodeFromString(value)
    }

    override fun put(bundle: Bundle, key: String, value: Review?) {
        if (value != null) bundle.putString(key, Json.encodeToString(Review.serializer(), value))
    }

    override fun serializeAsValue(value: Review?): String {
        return value
            ?.let {
                Json.encodeToString(
                    serializer = Review.serializer(),
                    value = it.copy(
                        meetingImageUrl = it.meetingImageUrl.encoding(),
                        images = it.images.map { image -> image.copy(imageId = image.imageUrl.encoding()) }
                    )
                )
            }
            ?: ""
    }
}