package com.pagingviewmodel.ui.componants.viewmodel

import android.content.Context
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.pagingviewmodel.data.remote.Repositry
import com.pagingviewmodel.model.ImageData

class MainViewModel(val context: Context) : ViewModel() {
    private var repositry = Repositry(context)

    //--------get photos from server--return livedata
    fun getPhotos(): LiveData<PagingData<ImageData>> {
        return repositry.getAllImages().cachedIn(viewModelScope)
    }


    //--------factory for viewmodel---
    class Factory(val context: Context) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MainViewModel(
                context
            ) as T
        }
    }
}