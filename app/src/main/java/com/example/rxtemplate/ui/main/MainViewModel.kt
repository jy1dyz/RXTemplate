package com.example.rxtemplate.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.rxtemplate.use_cases.ItemUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val useCase: ItemUseCase
) : ViewModel() {

    private val disposable = CompositeDisposable()




    fun fetchPosts() {

//        viewModelScope.launch {
//            Log.w("TAG", "fetchPosts")
//            try {
//                useCase()
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//        }
        disposable.add(
            useCase().subscribe {
                Log.w("TAG", "${it.size}")
            }
        )

    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}