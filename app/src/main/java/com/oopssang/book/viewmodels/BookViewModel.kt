package com.oopssang.book.viewmodels;

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.oopssang.book.data.SearchBookResponse


class BookViewModel : ViewModel() {

    private val response = MutableLiveData<SearchBookResponse>()

    fun getSearchBookResponse(): MutableLiveData<SearchBookResponse> {
        return response
    }
}