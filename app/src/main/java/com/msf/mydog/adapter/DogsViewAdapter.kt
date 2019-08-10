package com.msf.mydog.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.msf.mydog.R
import com.msf.mydog.ui.CollectionFragmentDirections
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.content_feed.view.*

class DogsViewAdapter(private val mValues: List<String>): RecyclerView.Adapter<DogsViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.content_feed, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dog = mValues[position]
        holder.bind(dog)
        with(holder.mView) {
            Picasso.get().load(dog).into(thumbnail)
            holder.mView.setOnClickListener {
                holder.mView.findNavController().navigate(CollectionFragmentDirections.actionCollectionFragmentToFullScreenDialogFragment(dog))
            }
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        fun bind(dog: String){
        }
    }
}