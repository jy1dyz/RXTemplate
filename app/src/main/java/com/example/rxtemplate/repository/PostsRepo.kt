package com.example.rxtemplate.repository

import com.example.rxtemplate.data.Item
import com.example.rxtemplate.network.ItemsApi
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PostsRepo @Inject constructor(private val api: ItemsApi) {

    /*suspend*/ fun fetchPosts(): Observable<List<Item>> /*= withContext(Dispatchers.IO) */{
//        return@withContext api.fetchPosts()
        return api.fetchPosts()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
    }
}