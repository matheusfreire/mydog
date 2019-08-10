package com.msf.mydog.ui


import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.msf.mydog.R
import com.msf.mydog.adapter.DogsViewAdapter
import com.msf.mydog.data.MyDogViewModel
import com.msf.mydog.databinding.FragmentFeedBinding
import com.msf.mydog.util.ItemDecoration


class FeedFragment : Fragment() {

    private lateinit var bindingFeedBinding: FragmentFeedBinding
    private var columnCount = 2
    private lateinit var viewModel: MyDogViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bindingFeedBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_feed, container, false)
        validateOrientation()
        with(bindingFeedBinding.recyclerViewFeed) {
            addItemDecoration(ItemDecoration(columnCount, dpToPx(10)))
        }
        viewModel = ViewModelProviders.of(requireActivity())[MyDogViewModel::class.java]
        return bindingFeedBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.takeIf { it.containsKey(DOG_TITLE) }?.apply {
            if (savedInstanceState == null) {
                setVisibilityViews(false)
            }
        }
    }

    private fun validateOrientation() {
        val orientation = resources.configuration.orientation
        columnCount = if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            4
        } else {
            2
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.liveDataFeed.observe(this, Observer {
            if (it.isNotEmpty()){
                bindingFeedBinding.recyclerViewFeed.adapter = DogsViewAdapter(it )
                setVisibilityViews(true)
            } else {
                showMessage()
            }
        })
        viewModel.liveDataIsLooking.observe(this, Observer {
            setVisibilityViews(!it)
        })
    }

    private fun showMessage() {
        bindingFeedBinding.progressLoading.visibility = View.GONE
        bindingFeedBinding.errorMessage.visibility = View.VISIBLE
    }

    private fun setVisibilityViews(visibilityOfRecycler: Boolean){
        bindingFeedBinding.progressLoading.visibility = if(visibilityOfRecycler) View.GONE else View.VISIBLE
        bindingFeedBinding.recyclerViewFeed.visibility = if(visibilityOfRecycler) View.VISIBLE else View.GONE
    }

    private fun dpToPx(dp: Int): Int {
        return (dp * resources.displayMetrics.density).toInt()
    }

    companion object {
        const val DOG_TITLE = "dog"
        const val IMAGE = "image"
    }

}
