package com.oopssang.book

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.oopssang.book.viewmodels.BookViewModel

class MainActivity : AppCompatActivity() {

    val manager = supportFragmentManager

    private val bookViewModel: BookViewModel by lazy {
        ViewModelProvider(this@MainActivity, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return BookViewModel() as T
            }
        }).get(BookViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val transaction = manager.beginTransaction()
        val fragment = SearchFragment()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}
