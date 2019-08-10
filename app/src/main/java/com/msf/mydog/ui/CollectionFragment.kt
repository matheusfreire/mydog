package com.msf.mydog.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.msf.mydog.R
import com.msf.mydog.adapter.PageAdapter
import com.msf.mydog.data.MyDogViewModel
import com.msf.mydog.databinding.FragmentCollectionBinding

class CollectionFragment: Fragment() {
    private lateinit var pageAdapter: PageAdapter
    private lateinit var dataBinding: FragmentCollectionBinding
    private lateinit var viewModel: MyDogViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_collection, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        pageAdapter = PageAdapter(childFragmentManager)
        viewModel = ViewModelProviders.of(requireActivity())[MyDogViewModel::class.java]
        dataBinding.viewpager.adapter = pageAdapter
        dataBinding.viewpager.offscreenPageLimit = dataBinding.tabLayout.tabCount
        dataBinding.viewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                viewModel.getFeed(dataBinding.viewpager.adapter!!.getPageTitle(position).toString())

            }

        })
    }

    override fun onResume() {
        super.onResume()
        dataBinding.viewpager.postDelayed({

            this.run() {
                dataBinding.viewpager.currentItem = 0
            }
        }, 10)
    }
}

