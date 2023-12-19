package com.example.ecoscan.data.remote.retrofit


import androidx.annotation.RawRes
import com.example.ecoscan.data.remote.response.ArticleResponseItem
import com.example.ecoscan.data.remote.response.AuthResponse
import com.example.ecoscan.data.remote.response.DetailResponse
import com.example.ecoscan.data.remote.response.DetailResultResponse
import com.example.ecoscan.data.remote.response.GetResultResponse
import com.example.ecoscan.data.remote.response.GetResultResponseItem
import com.example.ecoscan.data.remote.response.PredictResponse
import com.example.ecoscan.data.remote.response.StoreImageResponse
import com.example.ecoscan.data.remote.response.StoreResultResponse
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
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
    ):AuthResponse


    @FormUrlEncoded
    @POST("auth/login")
    suspend fun login (
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

    @Multipart
    @POST("/scan-result/upload")
    suspend fun storeImage(
        @Part file: MultipartBody.Part
    ): StoreImageResponse
    @FormUrlEncoded

    @PATCH("/store-result")
    suspend fun storeResult(
//        @Header("authorization") token: String,
        @Field("calcium") calcium: String,
        @Field("carbohydrates") carbohydrates: String,
        @Field("emission") emissions: String,
        @Field("fat") fat: String,
        @Field("food_name") food_name: String,
        @Field("protein") protein: String,
        @Field("vitamins") vitamins: String,
        @Field("image_url") imageUrl: String
    ): StoreResultResponse

    @PATCH("/get-result-info")
    suspend fun getResult():List<GetResultResponseItem>
    @PATCH("/get-result-info/{id}")
    suspend fun getDetailResultById(
        @Path("id") id: String
    ): DetailResultResponse

}