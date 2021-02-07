package com.example.data.repository

import arrow.fx.IO
import com.example.data.model.Photo
import com.example.data.network.NetworkDataSource

interface HomeRepository {

    fun getComments(): IO<List<Photo>>

    companion object {

        operator fun invoke(dataSource: NetworkDataSource) =
            object : HomeRepository {

                override fun getComments(): IO<List<Photo>> =
                    IO.effect { dataSource.getComments() }
            }
    }
}
