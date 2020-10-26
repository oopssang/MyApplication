/*
 * Copyright 2018 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.samples.apps.sunflower

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.oopssang.book.R
import com.oopssang.book.api.service.SearchBookService
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class SearchFragment : Fragment() {

    var preText = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        val et_search: EditText? = view.findViewById(R.id.et_search)
        et_search!!.addTextChangedListener(object : TextWatcher {
            //변경되기전 문자열을 담고있다.
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable) {
                preText = s.toString()
                Handler(Looper.getMainLooper()).postDelayed({
                    GlobalScope.launch(Dispatchers.Main) {
                        val response = SearchBookService.create().searchBook(preText, 1, 50)
                    }
                }, 500)
            }
        })

        return view
    }

//    protected override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//        editText = findViewById(R.id.editText)
//        editText.addTextChangedListener(object : TextWatcher {
//            //변경되기전 문자열을 담고있다.
//            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
//                preText = s.toString()
//            }
//
//            //텍스트가 변경될때 마다 호출된다. 보통은 이 함수안에 이벤트를 많이 사용하는것 같다.
//            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
////밑의 editText.setText(number+""); 가 실행되면 onTextChanged()함수가 계속해서 다시 호출 되기 때문에 추가했다.
//                if (s.toString() == preText) return  //editText에 포커스가 되어있고 텍스트가 하나라도 입력되어 있을때 동작하기 위해서 추가.
//                if (editText.isFocusable() && s.toString() != "") {
//                    try {
//                        number = editText.getText().toString().toInt()
//                    } catch (e: NumberFormatException) {
//                        e.printStackTrace()
//                        return
//                    }
//                    //100이 넘을 경우 100으로 변경
//                    if (number > 100) {
//                        number = 100
//                    }
//                    editText.setText(number.toString() + "")
//                }
//            } //텍스트가 변경된 이후에 호출.
//
//            override fun afterTextChanged(s: Editable) {}
//        })
//    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onDetach() {
        super.onDetach()
    }
}
