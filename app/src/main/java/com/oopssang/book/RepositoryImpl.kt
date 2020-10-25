package com.oopssang.book

class RepositoryImpl : Repository {
    override fun getMyData(): String {
        return "Hello Koin"
    }
}