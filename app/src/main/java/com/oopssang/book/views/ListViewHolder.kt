package com.oopssang.book.views

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.oopssang.book.R
import com.oopssang.book.viewmodels.BookViewModel

class ListViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
    private val iv_bookimage = itemView?.findViewById<ImageView>(R.id.iv_bookimage)
    private val iv_like = itemView?.findViewById<ImageView>(R.id.iv_like)
    private val tv_bookname = itemView?.findViewById<TextView>(R.id.tv_bookname)
    private val tv_date = itemView?.findViewById<TextView>(R.id.tv_date)
    private val tv_info = itemView?.findViewById<TextView>(R.id.tv_info)
    private val tv_price = itemView?.findViewById<TextView>(R.id.tv_price)


    fun bind(context: Context, lifecycleOwner: LifecycleOwner, viewModel: BookViewModel, position: Int) {
        viewModel.getSearchBookResponse().observe(lifecycleOwner, Observer {
            Log.d("test", "BookViewModel ListViewHolder")
            tv_bookname!!.text = it.documents.get(position).title
            tv_date!!.text = it.documents.get(position).datetime.substring(0,10)
            tv_info!!.text = it.documents.get(position).contents
            tv_price!!.text = it.documents.get(position).price.toString()
        })
    }
}