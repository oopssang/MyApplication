package com.oopssang.book.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oopssang.book.api.service.SearchBookService
import kotlinx.coroutines.launch


class SearchViewModel(
    private val loginRepository: LoginRepository
): ViewModel() {

    fun search(bookName: String) {

        // Create a new coroutine on the UI thread
        viewModelScope.launch {

            // Make the network call and suspend execution until it finishes
            val response = SearchBookService.create().searchBook(bookName, 1, 50)

            // Display result of the network request to the user
            when (response) {


            }
        }
    }
}

