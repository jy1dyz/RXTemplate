package com.example.rxtemplate.use_cases

import com.example.rxtemplate.data.Item
import com.example.rxtemplate.repository.PostsRepo
import io.reactivex.Observable
import javax.inject.Inject


class ItemUseCase @Inject constructor(
    private val repo: PostsRepo
) {

    /*suspend*/ operator fun invoke(): Observable<List<Item>> {
        return repo.fetchPosts()
    }
}