package com.zeus.compose.data

import com.zeus.compose.data.api.EndPoints
import com.zeus.compose.data.dto.Result
import com.zeus.compose.data.dto.StreamingContentDto
import com.zeus.compose.data.dto.UserCredentialsDto
import com.zeus.compose.data.dto.UserDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RetrofitServices {

    @POST(EndPoints.LOGIN)
    suspend fun login(@Body userCredentialsDto: UserCredentialsDto): UserDto

    @GET(EndPoints.VALIDATE_SESSION)
    suspend fun validateSession(): Boolean

    @GET(EndPoints.HOME)
    suspend fun getHomeContent(): Result<List<StreamingContentDto>>
}