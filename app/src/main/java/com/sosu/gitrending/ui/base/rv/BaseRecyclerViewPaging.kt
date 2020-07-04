package com.sosu.gitrending.ui.base.rv

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

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

        val layoutManager = recyclerView.layoutManager ?: return

        val visibleItemCount = layoutManager.childCount
        val totalItemCount = layoutManager.itemCount
        var firstVisibleItemPosition = 0

        when (recyclerView.layoutManager) {
            is StaggeredGridLayoutManager -> {
                val firstVisibleItemPositions =
                    (recyclerView.layoutManager as StaggeredGridLayoutManager).findFirstVisibleItemPositions(null)
                // get maximum element within the list
                firstVisibleItemPosition = firstVisibleItemPositions[0]
            }
            is GridLayoutManager -> {
                firstVisibleItemPosition =
                    (recyclerView.layoutManager as GridLayoutManager).findFirstVisibleItemPosition()
            }
            is LinearLayoutManager -> {
                firstVisibleItemPosition =
                    (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
            }
        }

        @Synchronized
        if (!isLoading
            && !isLastPage()
            && visibleItemCount + firstVisibleItemPosition >= totalItemCount
            && firstVisibleItemPosition >= 0) {
            isLoading = true

            loadMoreItems(getPage())
        }
    }

    fun onCompletedNextPage(){
        isLoading = false
    }
}