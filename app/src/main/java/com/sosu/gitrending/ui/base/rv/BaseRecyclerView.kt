package com.sosu.gitrending.ui.base.rv

import android.content.Context
import android.view.View
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

/**
 * Created by hyunsuso on 2020/07/04.
 */
class BaseRecyclerView constructor(
    private val context: Context?,
    private val recyclerView: RecyclerView,
    private val recyclerViewAdapterImpl: BaseRecyclerViewAdapterImpl<*, *>
) {

    companion object{
        val TAG = BaseRecyclerView::class.java.simpleName

        const val PAGE_START = 0

        const val PAGING_DIFF_HEIGHT = 300
    }

    enum class LayoutManagerType{
        LINEAR, HORIZONTAL, GRID
    }

    /*
    * support layout manager
    * 1 linear
    * 2 grid
    * */
    private var linearLayoutManager: LinearLayoutManager? = null
    private var gridLayoutManager: GridLayoutManager? = null
    private var staggeredGridLayoutManager : StaggeredGridLayoutManager? = null

    private var layoutManagerType : LayoutManagerType = LayoutManagerType.LINEAR

    /*
    * paging listener
    * */
    private var baseRecyclerViewPaging : BaseRecyclerViewPaging? = null

    var page = PAGE_START
    var isLastPage = false

    init {
        recyclerView.adapter = recyclerViewAdapterImpl
    }

    // recycler view linear or horizontal
    // 1. LinearLayoutManager.HORIZONTAL
    // 2. LinearLayoutManager.VERTICAL  (default)
    fun initLinearLayoutManager(orientation: Int) {
        linearLayoutManager = LinearLayoutManager(context)

        when(orientation){
            LinearLayoutManager.VERTICAL -> {
                setLayoutManagerType(LayoutManagerType.LINEAR)
                linearLayoutManager?.orientation = LinearLayoutManager.VERTICAL
            }
            LinearLayoutManager.HORIZONTAL -> {
                setLayoutManagerType(LayoutManagerType.HORIZONTAL)
                linearLayoutManager?.orientation = LinearLayoutManager.HORIZONTAL
            }
            else ->{
                setLayoutManagerType(LayoutManagerType.LINEAR)
                linearLayoutManager?.orientation = LinearLayoutManager.VERTICAL
            }
        }

        recyclerView.layoutManager = linearLayoutManager
    }

    // recycler view grid
    fun initGridLayoutManager(_cols: Int) {
        setLayoutManagerType(LayoutManagerType.GRID)

        var cols = _cols
        if (cols <= 0) {
            cols = 1
        }
        gridLayoutManager = GridLayoutManager(context, cols)
        recyclerView.layoutManager = gridLayoutManager
    }

    fun initStaggeredGridLayoutManager(_cols: Int){
        var cols = _cols
        if (cols <= 0) {
            cols = 1
        }

        this.staggeredGridLayoutManager = StaggeredGridLayoutManager(_cols, 1)

        recyclerView.layoutManager = staggeredGridLayoutManager
    }

    fun getGridCol() : Int{
        if(gridLayoutManager != null){
            return gridLayoutManager?.spanCount ?: 1
        } else if(staggeredGridLayoutManager != null){
            return staggeredGridLayoutManager?.spanCount ?: 1
        }
        return 1
    }

    fun setRecyclerViewAdapter(recyclerViewAdapterImpl: BaseRecyclerViewAdapterImpl<*, *>){
        recyclerView.adapter = recyclerViewAdapterImpl
    }

    /*
    * set layout manager type
    * called only when init layout manager
    * */
    private fun setLayoutManagerType(layoutManagerType : LayoutManagerType){
        this.layoutManagerType = layoutManagerType
    }

    /*
    * set paging listener
    * */
    fun setOnPagingListener(pagingListener: BaseRecyclerView.PagingListener){
        baseRecyclerViewPaging = object : BaseRecyclerViewPaging(){
            override fun getPage(): Int {
                return page
            }

            override fun isLastPage(): Boolean {
                return isLastPage
            }

            override fun loadMoreItems(currentPage: Int) {
                pagingListener.onNextPage(currentPage)
            }
        }
        recyclerView.addOnScrollListener(baseRecyclerViewPaging!!)
    }

    /*
    * set paging listener on nestedScrollView
    * */
    fun setOnPagingListener(nestedScrollView : NestedScrollView, pagingListener: BaseRecyclerView.PagingListener){
        recyclerView.isNestedScrollingEnabled = false

        nestedScrollView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            val childView: View = nestedScrollView.getChildAt(nestedScrollView.getChildCount() - 1)
            val diff: Int = childView.bottom - (nestedScrollView.getHeight() + scrollY)
            if (diff < PAGING_DIFF_HEIGHT && !isLastPage) {
                pagingListener.onNextPage(page)
            }
        })

        baseRecyclerViewPaging = object : BaseRecyclerViewPaging(){
            override fun getPage(): Int {
                return page
            }

            override fun isLastPage(): Boolean {
                return isLastPage
            }

            override fun loadMoreItems(currentPage: Int) {
                pagingListener.onNextPage(currentPage)
            }
        }
        recyclerView.addOnScrollListener(baseRecyclerViewPaging!!)
    }

    fun onScrolledTop(){
        recyclerView.scrollToPosition(0)
    }

    /*
    * init page flags
    * */
    fun initPageFlags(){
        recyclerViewAdapterImpl.removeItems()

        this.page = PAGE_START
        this.isLastPage = false
    }

    /*
    * completed next page call
    * if success, currentPage is next page
    * if failed, currentPage is called page
    * */
    fun onCompletedNextPage(currentPage : Int){
        this.page = currentPage

        baseRecyclerViewPaging?.onCompletedNextPage()
    }

    /*
    * called last page
    * */
    fun onLastPage(){
        this.isLastPage = true
    }

    interface PagingListener {
        fun onNextPage(currentPage: Int)
    }
}

