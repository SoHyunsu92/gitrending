package com.sosu.gitrending.ui.base.rv

/**
 * Created by hyunsuso on 2020/07/04.
 */
interface BaseRecyclerViewAdapter<T> {
    fun getItem(position: Int): T?
    fun addItem(item: T)
    fun addAllItem(items: List<T>)
    fun setItems(items: List<T>)
    fun updateItem(item : T)
    fun removeItem(item : T)
    fun removeItems()

    fun sort(comparator : Comparator<T>)
}