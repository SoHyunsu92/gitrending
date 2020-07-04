package com.sosu.gitrending.ui.base

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.sosu.gitrending.di.viewmodel.ViewModelFactory
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

/**
 * Created by hyunsuso on 2020/07/04.
 */
abstract class BaseActivity<DB : ViewDataBinding, VM : BaseViewModel<*>>
    : AppCompatActivity(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewDataBinding: DB
    private lateinit var viewModel: VM

    abstract fun getName(): String
    abstract fun getBindingVariable(): Int
    abstract fun getLayoutId(): Int
    abstract fun getViewModel(): VM
    abstract fun initView()
    abstract fun initAfterBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())

        this.viewModel = getViewModel()

        performDependencyInjection()
        performDataBinding()

        initView()
        initAfterBinding()
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return dispatchingAndroidInjector
    }

    // inject activity
    private fun performDependencyInjection() {
        AndroidInjection.inject(this)
    }

    // bind data binding
    private fun performDataBinding(){
        this.viewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        this.viewDataBinding.setVariable(getBindingVariable(), this.viewModel)
        this.viewDataBinding.lifecycleOwner = this
        this.viewDataBinding.executePendingBindings()
    }

    // get data binding
    fun getViewDataBinding(): DB{
        return viewDataBinding
    }
}