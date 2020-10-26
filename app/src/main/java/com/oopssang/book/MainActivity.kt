package com.oopssang.book

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.samples.apps.sunflower.SearchFragment
import com.kakao.sdk.common.util.Utility

class MainActivity : AppCompatActivity() {

    val manager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var keyHash = Utility.getKeyHash(this)
        Log.d("test", keyHash)

        val transaction = manager.beginTransaction()
        val fragment = SearchFragment()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
