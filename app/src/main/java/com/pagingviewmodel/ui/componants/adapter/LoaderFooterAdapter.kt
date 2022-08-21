package com.pagingviewmodel.ui.componants.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pagingviewmodel.databinding.RowLoaderFooterBinding

class LoaderFooterAdapter(private val retry:()->Unit) : LoadStateAdapter<LoaderFooterAdapter.LoaderFooterViewHolder>() {


    override fun onBindViewHolder(holder: LoaderFooterViewHolder, loadState: LoadState) {
        holder.bindTo(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): LoaderFooterViewHolder {
        val binding =
            RowLoaderFooterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LoaderFooterViewHolder(binding)
    }

    inner class LoaderFooterViewHolder(private val thisbinding: RowLoaderFooterBinding) :
        RecyclerView.ViewHolder(thisbinding.root) {
        init {
            thisbinding.btnRetry.setOnClickListener {
                retry.invoke()
            }
        }

        fun bindTo(loadState: LoadState) {

            thisbinding.apply {
                progressbar.isVisible = loadState is LoadState.Loading
                tvError.isVisible = loadState !is LoadState.Loading
                btnRetry.isVisible = loadState !is LoadState.Loading
            }
        }
    }
}