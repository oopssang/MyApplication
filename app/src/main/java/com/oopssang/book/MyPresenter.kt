package com.oopssang.book


class MyPresenter(private val repository: Repository) {
    fun sayHello() = "${repository.getMyData()} from $this"
}