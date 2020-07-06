package com.sosu.gitrending.ui.base

import android.content.Context
import android.content.res.Configuration
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.RelativeLayout
import android.widget.Toast

/**
 * Created by hyunsuso on 2020/07/06.
 */
abstract class BaseView : RelativeLayout {
    
    abstract fun getName(): String
    abstract fun layoutResId(): Int
    abstract fun initView()
    abstract fun initInfo()

    constructor(context: Context) : super(context){
        inflateViews(context)
    }
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs){
        inflateViews(context)
    }
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        inflateViews(context)
    }

    fun inflateViews(context: Context) {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(layoutResId(), this)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()

        initView()
        initInfo()
    }
    
    protected fun hideRootView() {
        this.visibility = View.GONE
    }
    
    protected fun getString(resId: Int): String {
        return if (context == null) {
            ""
        } else context.getString(resId)
    }
}
