package com.dicoding.submission_2

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @Headers("Authorization: token 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")
    @GET("users")
    fun getUsers(): Call<List<UserResponseItem>>

    @GET("search/users")
    @Headers("Authorization: token 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")
    fun getSearchUser(
        @Query("q") query: String
    ): Call<UserResponse>

    @GET("users/{username}")
    @Headers("Authorization: token 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")
    fun getUserDetail(
        @Path("username") username: String
    ): Call<UserResponseItem>

    @GET("users/{username}/followers")
    @Headers("Authorization: token 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")
    fun getFollowers(
        @Path("username") username: String
    ): Call<List<UserResponseItem>>

    @GET("users/{username}/following")
    @Headers("Authorization: token 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")
    fun getFollowing(
        @Path("username") username: String
    ): Call<List<UserResponseItem>>


}