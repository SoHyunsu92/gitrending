package com.sosu.gitrending.ui.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.lang.ref.WeakReference

/**
 * Created by hyunsuso on 2020/07/04.
 */
abstract class BaseViewModel<N> : ViewModel() {

    abstract fun getName(): String

    // for clear all Observable
    private val compositeDisposable = CompositeDisposable()

    // for clear memory with gc
    private lateinit var navigator: WeakReference<N>

    override fun onCleared() {
        super.onCleared()

        compositeDisposable.dispose()
    }

    fun getCompositeDisposable(): CompositeDisposable {
        return this.compositeDisposable
    }

    fun addCompositeDisposable(disposable: Disposable?){
        if(disposable == null){
            return
        }
        compositeDisposable.add(disposable)
    }

    fun getNavigator(): N? {
        return navigator.get()
    }

    fun setNavigator(navigator : N){
        this.navigator = WeakReference<N>(navigator)
    }
}