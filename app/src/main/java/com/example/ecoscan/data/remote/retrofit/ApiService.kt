package com.example.ecoscan.data.remote.retrofit


import com.example.ecoscan.data.models.Paket
import com.example.ecoscan.data.remote.response.ArticleResponseItem
import com.example.ecoscan.data.remote.response.AuthResponse
import com.example.ecoscan.data.remote.response.DetailResponse
import com.example.ecoscan.data.remote.response.PaketResponse
import com.example.ecoscan.data.remote.response.PredictResponse
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface ApiService {

    @FormUrlEncoded
    @POST("auth/signUp")
    suspend fun register(
        @Field("username") name: String,
        @Field("firstName") fistName: String,
        @Field("lastName") lastName: String,
        @Field("password") password: String
    ): AuthResponse


    @FormUrlEncoded
    @POST("auth/login")
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String,
    ): AuthResponse

    @GET("/article")
    suspend fun getAllArticle(): List<ArticleResponseItem>

    @GET("/article/{id}")
    suspend fun getDetailArticle(
        @Path("id") id: String
    ): DetailResponse


    @Multipart
    @POST("predict/")
    suspend fun scanPredict(
        @Part image: MultipartBody.Part
    ): PredictResponse

    //    PATCH PAKET QUOTA
    @FormUrlEncoded
    @PATCH("/quota/add")
    suspend fun paketQuota(
        @Field("package") paket: String
    ): PaketResponse

}