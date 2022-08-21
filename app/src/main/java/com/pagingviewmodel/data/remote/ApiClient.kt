package com.pagingviewmodel.data.remote

import com.pagingviewmodel.model.ImageData
import com.pagingviewmodel.utills.Config
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClient {

    @GET(Config.LIST) //--------to get all photos----
    suspend fun getMyPhotos(
        @Query("page") page: Int?,
    ):List<ImageData>

}