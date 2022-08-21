package com.pagingviewmodel.data.remote

import android.content.Context
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.pagingviewmodel.data.paging.PhotosPagingSource

class Repositry(val context: Context) {
    var api: ApiClient? = null

    init {
        api = ServiceGenerator.instance?.clientService
    }

    //----------return data from datasource-----
    fun getAllImages() =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { PhotosPagingSource(context, api!!) }
        ).liveData
}