package com.msf.mydog.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.msf.mydog.ui.FeedFragment

class PageAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    override fun getCount(): Int  = 4

    private val titles = arrayOf("Husky", "Hound", "Pug", "Labrador")
    override fun getItem(i: Int): Fragment {
        val fragment = FeedFragment()
        fragment.arguments = Bundle().apply {
            putString(FeedFragment.DOG_TITLE, getPageTitle(i).toString())
        }
        return fragment
    }

    override fun getPageTitle(position: Int): CharSequence {
        return titles[position]
    }
}