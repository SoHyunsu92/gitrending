package com.sosu.gitrending.di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

/**
 * Created by hyunsuso on 2020/07/04.
 *
 * view model factory
 * for CustomViewModel extends viewModel 를 생성자를 통해서 생성할 수 없기 때문에
 */
@Singleton
class ViewModelFactory @Inject constructor(
    private val creators: @JvmSuppressWildcards Map<Class<out ViewModel>, Provider<ViewModel>>
) : ViewModelProvider.Factory {

    // view model create
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        var creator: Provider<out ViewModel>? = creators[modelClass]

        if(creator == null){
            for((key, value) in creators){
                if(modelClass.isAssignableFrom(key)){
                    creator = value
                    break
                }
            }
        }

        if(creator == null){
            throw IllegalArgumentException("unknown model class $modelClass")
        }
        try {
            @Suppress("UNCHECKED_CAST")
            return creator.get() as T
        } catch (e : Exception){
            throw RuntimeException(e)
        }
    }
}
