package com.app.whoiswatching

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class WhoIsWatchingAdapter(val onClickListener: OnClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val list = listOf(R.drawable.ic_netflix_user, R.drawable.ic_children)
    private val ADD_BUTTON = 1
    private val OTHER_USER = 0

    inner class WhoIsWatchingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    inner class AddButtonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        OTHER_USER ->
            WhoIsWatchingViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_who_is_watching, parent, false)
            )
        ADD_BUTTON ->
            AddButtonViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_add_button, parent, false)
            )
        else -> throw IllegalArgumentException()
    }

    override fun getItemCount() = list.size + 1

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is WhoIsWatchingViewHolder) {
            holder.itemView.findViewById<ImageView>(R.id.imageViewIcon).apply {
                setImageResource(list[position])
                setOnClickListener(onClickListener)
            }
        }
    }

    override fun getItemViewType(position: Int) = if (position == list.size) {
        ADD_BUTTON
    } else {
        OTHER_USER
    }
}