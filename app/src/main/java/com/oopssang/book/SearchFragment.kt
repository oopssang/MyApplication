package com.oopssang.book

import android.app.AlertDialog
import android.content.Context.INPUT_METHOD_SERVICE
import android.content.DialogInterface
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.oopssang.book.adapter.BookAdapter
import com.oopssang.book.api.service.SearchBookService
import com.oopssang.book.viewmodels.BookViewModel
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var bookViewModel: BookViewModel

    private var page = 1
    private var isAdd = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        return view
    }

    interface onItemClick{
        fun onClick(position: Int)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val factory: ViewModelProvider.Factory = NewInstanceFactory()
        bookViewModel = requireActivity()?.let { ViewModelProvider(it, factory).get(BookViewModel::class.java) }!!

        bookViewModel.getSearchBookResponse().observe(viewLifecycleOwner, Observer {
            if(isAdd) {
                (viewAdapter as BookAdapter).addAll(it.documents)
            } else {
                (viewAdapter as BookAdapter).replaceAll(it.documents)
            }
            viewAdapter.notifyDataSetChanged()
        })

        viewManager = LinearLayoutManager(context)
        viewAdapter = BookAdapter(context!!, object : onItemClick {
            override fun onClick(position: Int) {
                Log.d("test", "onItemClick() position : " + position)
                val fragment = DetailFragment.newInstance(position)
                val transaction = activity?.supportFragmentManager?.beginTransaction()
                transaction?.replace(R.id.container, fragment)
                transaction?.addToBackStack("detail")
                transaction?.commit()
            }
        })

        recyclerView = view.findViewById<RecyclerView>(R.id.rv_result).apply {
            layoutManager = viewManager
            adapter = viewAdapter
        }

        // 스크롤 감지
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    isAdd = true
                    searchBook()
                    hideKeyboard()
                }
            }
        })

        btn_type.setOnClickListener {
            val dialog = AlertDialog.Builder(context)
                .setTitle("뷰 타입정하기")
                .setNegativeButton("텍스트우선", DialogInterface.OnClickListener { dialog, which ->
                    viewManager = LinearLayoutManager(context)
                    recyclerView.layoutManager = viewManager
                    (viewAdapter as BookAdapter).viewType = 0
                    btn_type.text = "텍스트 우선"
                    viewAdapter.notifyDataSetChanged()}
                )
                .setPositiveButton("이미지우선", DialogInterface.OnClickListener { dialog, which ->
                    viewManager = GridLayoutManager(context, 2)
                    recyclerView.layoutManager = viewManager
                    (viewAdapter as BookAdapter).viewType = 1
                    btn_type.text = "이미지우선"
                    viewAdapter.notifyDataSetChanged()}
                )
                .create()
            dialog.show()
        }

        iv_search.setOnClickListener {
            isAdd = false
            searchBook()
            hideKeyboard()
        }
    }

    override fun onResume() {
        super.onResume()
        et_search.addTextChangedListener(textWatcher)
    }

    override fun onStop() {
        super.onStop()
        et_search.removeTextChangedListener(textWatcher)
    }

    private val textWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
        }
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            isAdd = false
            searchBook()
        }
    }

    fun searchBook(){
        GlobalScope.launch(Dispatchers.Main) {
            val name = et_search.text.toString()
            if(name?.length > 0){
                val response = SearchBookService.create().searchBook(name, page, 50)
                bookViewModel.getSearchBookResponse().postValue(response)
            }
        }
    }

    fun hideKeyboard(){
        val imm: InputMethodManager? = requireActivity().getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager?
        imm?.hideSoftInputFromWindow(et_search?.getWindowToken(), 0);
    }

}
