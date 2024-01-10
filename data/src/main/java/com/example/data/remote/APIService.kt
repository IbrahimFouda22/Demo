package com.example.data.remote

import com.example.data.dto.CategoryDto
import com.example.data.dto.LoginDto
import com.example.data.dto.PopularDto
import com.example.data.dto.TrendingDto
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface APIService {

    @FormUrlEncoded
    @POST("login")
    suspend fun signIn(
        @Field("email") email:String,
        @Field("password") password: String,
        @Field("device_token")deviceToken: String
    ): Response<LoginDto>

    @FormUrlEncoded
    @POST("client-register")
    suspend fun signUp(
        @Field("email") email:String,
        @Field("name") name:String,
        @Field("password") password: String,
        @Field("phone")phone: String
    ): Response<LoginDto>

    @GET("home-base-categories")
    suspend fun getHomeCategory(
    ): Response<CategoryDto>

    @GET("popular-sellers")
    suspend fun getHomePopular(
        @Query("lat") lat:Double,
        @Query("lng") lng:Double,
        @Query("filter") filter:Int,
    ): Response<PopularDto>

    @GET("trending-sellers")
    suspend fun getHomeTrending(
        @Query("lat") lat:Double,
        @Query("lng") lng:Double,
        @Query("filter") filter:Int,
    ): Response<TrendingDto>
}