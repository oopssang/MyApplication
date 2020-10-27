package com.oopssang.book.adapter

import Documents
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.oopssang.book.R
import com.oopssang.book.viewmodels.BookViewModel
import com.oopssang.book.views.GridViewHolder
import com.oopssang.book.views.ListViewHolder

class BookAdapter(val context: Context, val lifecycleOwner: LifecycleOwner, val BookViewModel: BookViewModel) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var viewType: Int = 0
    lateinit var itemlist: List<Documents>


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        BookViewModel.getSearchBookResponse().observe(lifecycleOwner, Observer {
            Log.d("test", "BookViewModel onCreateViewHolder")
            itemlist = it.documents
        })
        return when (viewType) {
            0 -> ListViewHolder(LayoutInflater.from(context).inflate(R.layout.listview_holder, parent, false))
            else -> GridViewHolder(LayoutInflater.from(context).inflate(R.layout.gridview_holder, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            0 -> (holder as ListViewHolder).bind(context, lifecycleOwner, BookViewModel, position)
            else -> (holder as GridViewHolder).bind(context, lifecycleOwner, BookViewModel)
        }
    }

    fun setItem(list: List<Documents>){
        itemlist = list
    }

    override fun getItemCount(): Int {
        if(::itemlist.isInitialized) {
            return itemlist?.size
        } else {
            return 0
        }
    }

    override fun getItemViewType(position: Int): Int {
        return viewType
    }


}