package com.pagingviewmodel.data.paging

import android.content.Context
import androidx.paging.PagingSource
import com.pagingviewmodel.data.remote.ApiClient
import com.pagingviewmodel.model.ImageData
import retrofit2.HttpException
import java.io.IOException

//-----------datasource, return resulted data that came from server-------
class PhotosPagingSource(var context: Context,val apiClient: ApiClient):PagingSource<Int, ImageData>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int,ImageData> {
        val position = params.key ?: 1

          return try {
            val response = apiClient.getMyPhotos(position)
            val photos = response
              LoadResult.Page(
                data = photos,
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (photos.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }


}