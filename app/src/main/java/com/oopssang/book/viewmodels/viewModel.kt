package com.oopssang.book.viewmodels

private val viewModel: MainViewModel by lazy {
    ViewModelProvider(this@MainActivity, object : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MainViewModel() as T
        }
    }).get(MainViewModel::class.java)
}