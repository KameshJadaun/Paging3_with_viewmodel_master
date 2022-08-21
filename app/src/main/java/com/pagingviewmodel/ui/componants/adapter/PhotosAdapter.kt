package com.pagingviewmodel.ui.componants.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.pagingviewmodel.databinding.RowPhotosBinding
import com.pagingviewmodel.model.ImageData

class PhotosAdapter :
    PagingDataAdapter<ImageData, PhotosAdapter.PhotosViewHolder>(PHOTO_COMPARIZATION) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosViewHolder {
        val binding = RowPhotosBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PhotosViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {
        if (getItem(position) != null) {
            holder.bindTo(getItem(position)!!)//---send data to viewholder according to position---
        }
    }

    //-----------viewholder class----------
    class PhotosViewHolder(private val thisbinding: RowPhotosBinding) :
        RecyclerView.ViewHolder(thisbinding.root) {
        fun bindTo(data: ImageData) {
            thisbinding.model = data //--------sending data to ui--
        }
    }

    companion object {
        //------------to check if data is same or not--------------
        private val PHOTO_COMPARIZATION = object : DiffUtil.ItemCallback<ImageData>() {
            override fun areItemsTheSame(oldItem: ImageData, newItem: ImageData): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: ImageData, newItem: ImageData): Boolean =
                oldItem == newItem
        }
    }

}