package com.example.potatoservice.model

import com.example.potatoservice.model.remote.ActivityDetail
import com.example.potatoservice.model.remote.ActivityResponse
import com.example.potatoservice.model.remote.MarkerData
import com.example.potatoservice.model.remote.UserInfo
import com.example.potatoservice.model.remote.UserInterest
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface APIService {

    @POST("/api/signup")
    fun sendUserInfo(
        @Body userInfo: UserInfo
    ): Call<Void>

    @POST("/api/interests")
    fun sendUserInterest(
        @Body userInterest: UserInterest
    ): Call<ResponseBody>

    @GET("/api/markers")
    fun getMarkers(): Call<List<MarkerData>>




    // API method to get activities
    @GET("/api/v1/activities")
    fun getActivities(
        @Query("page") page: Int,
        @Query("size") size: Int? = null,
        @Query("sort") sort: String? = null,
        @Query("beforeDeadlineOnly") beforeDeadlineOnly: Boolean? = null,
        @Query("teenPossibleOnly") teenPossibleOnly: Boolean? = null,
        @Query("category") category: String? = null
    ): Call<ActivityResponse>

    @GET("/api/v1/activities/{activity_id}")
    fun getActivityDetail(
        @Path("activity_id") activityId: Int
    ): Call<ActivityDetail>
}
