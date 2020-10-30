package com.oopssang.book.views

import Documents
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.oopssang.book.R
import com.oopssang.book.SearchFragment
import com.squareup.picasso.Picasso

class GridViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {

    private val iv_bookimage = itemView?.findViewById<ImageView>(R.id.iv_bookimage)
    private val iv_like = itemView?.findViewById<ImageView>(R.id.iv_like)
    private val tv_bookname = itemView?.findViewById<TextView>(R.id.tv_bookname)


    fun bind(data: Documents, onItemClick: SearchFragment.onItemClick) {
        itemView.setOnClickListener {
            onItemClick.onClick(adapterPosition)
        }

        if (!data.thumbnail.isEmpty()) {
            Picasso.get()
                .load(data.thumbnail)
                .into(iv_bookimage)
        }

        setLike(data)
        iv_like?.setOnClickListener {
            data.isLike = !data.isLike
            setLike(data)
        }

        tv_bookname!!.text = data.title
    }

    private fun setLike(data: Documents){
        if(data.isLike){
            Picasso.get()
                .load(R.mipmap.star)
                .into(iv_like)
        } else {
            Picasso.get()
                .load(R.mipmap.star_dim)
                .into(iv_like)
        }
    }
}