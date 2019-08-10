package com.msf.mydog.ui

import android.os.Bundle
import androidx.fragment.app.DialogFragment
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import androidx.databinding.DataBindingUtil
import com.msf.mydog.R
import com.msf.mydog.databinding.FragmentFullscreenBinding
import com.squareup.picasso.Picasso


class FullScreenDialogFragment: DialogFragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.dialog_theme)
    }

    override fun onStart() {
        super.onStart()
        val d = dialog
        if (d != null) {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.MATCH_PARENT
            d.window!!.setLayout(width, height)
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        val dataBinding = DataBindingUtil.inflate<FragmentFullscreenBinding>(inflater, R.layout.fragment_fullscreen, container, false)
        val args  = arguments?.let { FullScreenDialogFragmentArgs.fromBundle(it) }
        Picasso.get().load(args!!.image).into(dataBinding.imgExpanded)
        return dataBinding.root
    }
}