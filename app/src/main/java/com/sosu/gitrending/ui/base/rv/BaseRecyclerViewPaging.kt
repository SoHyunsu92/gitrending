package com.sosu.gitrending.ui.base.rv

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by hyunsuso on 2020/07/04.
 */
abstract class BaseRecyclerViewPaging : RecyclerView.OnScrollListener(){

    companion object{
        val TAG = BaseRecyclerViewPaging::class.java.simpleName
    }

    abstract fun getPage() : Int
    abstract fun isLastPage() : Boolean
    private var isLoading : Boolean = false

    abstract fun loadMoreItems(currentPage : Int)

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val layoutManager = LinearLayoutManager::class.java.cast(recyclerView.layoutManager)
        val totalItemCount = layoutManager!!.itemCount
        val lastVisible = layoutManager.findLastCompletelyVisibleItemPosition()

        @Synchronized
        if (!isLoading
            && !isLastPage()
            && lastVisible >= totalItemCount - 1) {
            isLoading = true

            loadMoreItems(getPage())
        }
    }

    fun onCompletedNextPage(){
        isLoading = false
    }
}