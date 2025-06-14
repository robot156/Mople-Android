package com.moim.core.network.service

import com.moim.core.datamodel.MeetingResponse
import com.moim.core.datamodel.ParticipantContainerResponse
import kotlinx.serialization.json.JsonObject
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface MeetingApi {

    @GET("meet/list")
    suspend fun getMeetings(): List<MeetingResponse>

    @GET("meet/{meetId}")
    suspend fun getMeeting(@Path("meetId") id: String): MeetingResponse

    @POST("meet/invite/{meetId}")
    suspend fun getMeetingInviteCode(@Path("meetId") id: String): String

    @GET("/meet/members/{meetId}")
    suspend fun getMeetingParticipants(@Path("meetId") id: String): ParticipantContainerResponse

    @POST("meet/create")
    suspend fun createMeeting(
        @Body params: JsonObject
    ): MeetingResponse

    @PATCH("meet/update/{meetId}")
    suspend fun updateMeeting(
        @Path("meetId") id: String,
        @Body params: JsonObject
    ): MeetingResponse

    @POST("meet/join/{meetCode}")
    suspend fun joinMeeting(
        @Path("meetCode") meetCode: String
    ): MeetingResponse

    @DELETE("meet/{meetId}")
    suspend fun deleteMeeting(@Path("meetId") id: String)
}