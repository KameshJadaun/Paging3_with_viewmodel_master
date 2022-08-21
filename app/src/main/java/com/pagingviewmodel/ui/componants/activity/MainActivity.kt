package com.pagingviewmodel.ui.componants.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.pagingviewmodel.R
import com.pagingviewmodel.databinding.ActivityMainBinding
import com.pagingviewmodel.ui.base.BaseBindingActivity
import com.pagingviewmodel.ui.componants.adapter.LoaderFooterAdapter
import com.pagingviewmodel.ui.componants.adapter.PhotosAdapter
import com.pagingviewmodel.ui.componants.viewmodel.MainViewModel
import com.pagingviewmodel.utills.Utility

class MainActivity : BaseBindingActivity() {
    private var viewModel: MainViewModel? = null
    private var binding: ActivityMainBinding? = null
    var adapter: PhotosAdapter? = null
    override fun setBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding?.lifecycleOwner = this //----set lifecyclerowner for binding--
        viewModel = ViewModelProvider(this, MainViewModel.Factory(this))//------viewmodel
            .get(MainViewModel::class.java)
    }

    override fun createActivityObject(savedInstanceState: Bundle?) {
        mActivity = this
    }

    override fun initializeObject() {
        adapter = PhotosAdapter()
        binding?.apply {
            recycler.setHasFixedSize(true)
            recycler.adapter = adapter!!.withLoadStateHeaderAndFooter(
                header = LoaderFooterAdapter { adapter!!.retry() },
                footer = LoaderFooterAdapter { adapter!!.retry() },
            )
        }


        if(Utility.isOnline(mActivity!!)){ //----check if internet connected or not-------
            getImagesData()
        }

    }
//----------get data from viewmodel----
    private fun getImagesData() {
        viewModel?.getPhotos()?.observe(binding?.lifecycleOwner!!, Observer {
            adapter?.submitData(binding?.lifecycleOwner?.lifecycle!!, it)//--------submit data to pagingadapter
        })
    }

    override fun setListeners() {

    }
}