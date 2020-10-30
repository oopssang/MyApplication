package com.oopssang.book.adapter

import Documents
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oopssang.book.R
import com.oopssang.book.SearchFragment
import com.oopssang.book.views.GridViewHolder
import com.oopssang.book.views.ListViewHolder

class BookAdapter(val context: Context, val onClickListener: SearchFragment.onItemClick) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var viewType: Int = 0
    private val items = mutableListOf<Documents>()

    fun replaceAll(newItems: List<Documents>?) {
        newItems?.let {
            items.clear()
            items.addAll(it)
        }
    }

    fun addAll(newItems: List<Documents>?) {
        newItems?.let {
            items.addAll(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when (viewType) {
            0 -> ListViewHolder(LayoutInflater.from(context).inflate(R.layout.listview_holder, parent, false))
            else -> GridViewHolder(LayoutInflater.from(context).inflate(R.layout.gridview_holder, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            0 -> (holder as ListViewHolder).bind(
                 items[position],
                onClickListener
            )
            else -> (holder as GridViewHolder).bind(
                items[position],
                onClickListener
            )
        }
    }


    override fun getItemCount(): Int {
        return items?.size
    }

    override fun getItemViewType(position: Int): Int {
        return viewType
    }


}