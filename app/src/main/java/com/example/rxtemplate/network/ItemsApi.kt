package com.example.rxtemplate.network

import com.example.rxtemplate.data.Item
import io.reactivex.Observable
import retrofit2.http.GET

interface ItemsApi {

    @GET("posts")
    /*suspend*/ fun fetchPosts(): Observable<List<Item>>
}