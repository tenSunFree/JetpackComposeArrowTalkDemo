package com.example.data.network

import com.example.data.model.Photo
import retrofit2.http.GET

interface HomeApi {
    @GET("comments?postId=1")
    suspend fun getComments(): List<Photo>
}
