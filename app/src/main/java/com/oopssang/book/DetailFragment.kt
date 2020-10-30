package com.oopssang.book

import Documents
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import com.oopssang.book.viewmodels.BookViewModel
import com.squareup.picasso.Picasso

class DetailFragment : Fragment() {

    companion object {
        const val KEY = "key"
        fun newInstance(position: Int) = DetailFragment().apply {
            arguments = Bundle().apply {
                putInt(KEY, position)
            }
        }
    }

    val position by lazy { requireArguments().getInt(KEY) }

    private lateinit var bookViewModel: BookViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val iv_bookimage = view?.findViewById<ImageView>(R.id.iv_bookimage)
        val iv_like = view?.findViewById<ImageView>(R.id.iv_like)
        val tv_bookname = view?.findViewById<TextView>(R.id.tv_bookname)
        val tv_date = view?.findViewById<TextView>(R.id.tv_date)
        val tv_info = view?.findViewById<TextView>(R.id.tv_info)
        val tv_publisher = view?.findViewById<TextView>(R.id.tv_publisher)
        val tv_price = view?.findViewById<TextView>(R.id.tv_price)

        val factory: ViewModelProvider.Factory = NewInstanceFactory()
        bookViewModel = requireActivity()?.let { ViewModelProvider(it, factory).get(BookViewModel::class.java) }!!
        bookViewModel.getSearchBookResponse().observe(viewLifecycleOwner, Observer {

            Log.d("test", "DetailFragment : bookViewModel")
            val data = it.documents[position]

            if (!data.thumbnail.isEmpty()) {
                Picasso.get()
                    .load(data.thumbnail)
                    .into(iv_bookimage)
            }

            setLike(iv_like, data)
            iv_like?.setOnClickListener {
                data.isLike = !data.isLike
                setLike(iv_like, data)
            }

            tv_publisher!!.text = data.publisher
            tv_bookname!!.text = data.title
            tv_date!!.text = data.datetime.substring(0,10)
            tv_info!!.text = data.contents
            tv_price!!.text = data.price.toString()
        })
    }

    fun setLike(iv_like: ImageView, data: Documents){
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
