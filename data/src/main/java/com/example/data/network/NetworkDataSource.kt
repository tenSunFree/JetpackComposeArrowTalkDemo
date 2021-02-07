package com.example.data.network

import com.example.data.model.Photo

interface NetworkDataSource {

    suspend fun getComments(): List<Photo>

    companion object {

        operator fun invoke(api: HomeApi) = object : NetworkDataSource {

            override suspend fun getComments(): List<Photo> =
                api.getComments()
        }
    }
}
