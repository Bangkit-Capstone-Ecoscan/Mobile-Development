package com.example.ecoscan.data.remote.retrofit

import com.example.ecoscan.data.remote.response.PredictResponse
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService2 {

    @Multipart
    @POST("predict/")
    suspend fun scanPredict(
        @Part image: MultipartBody.Part
    ): PredictResponse

}