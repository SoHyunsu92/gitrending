package com.sosu.gitrending.ui.base.rv

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import javax.inject.Singleton

/**
 * Created by hyunsuso on 2020/07/04.
 */
abstract class BaseRecyclerViewAdapterImpl<T, VH : BaseRecyclerViewAdapterImpl.BaseViewHolder<T>>
    : RecyclerView.Adapter<VH>(), BaseRecyclerViewAdapter<T> {

    companion object{
        val TAG = BaseRecyclerViewAdapterImpl::class.java.simpleName
    }

    private val items = ArrayList<T>()
    // prevent overlap
    private val itemHashMap = HashMap<Int, T>()

    abstract fun getLayoutId(viewType : Int): Int

    // viewHolder
    abstract class BaseViewHolder<T> constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun onBind(item: T)
    }

    override fun onBindViewHolder(holder: VH, position: Int){
        holder.onBind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): T? {
        if (0 <= position && position < items.size){
            return items[position]
        }
        return null
    }

    override fun getItemIdx(item: T): Int {
        return items.indexOf(item)
    }

    override fun addItem(item: T){
        if(itemHashMap.containsKey(item.hashCode())){
            return
        }
        putItemHashMap(item)

        items.add(item)

        notifyItemInserted(items.size - 1)
    }

    override fun addAllItem(items: List<T>) {
        val insertList = ArrayList<T>()
        for(item in items){
            if(itemHashMap.containsKey(item.hashCode())){
                continue
            }
            putItemHashMap(item)

            insertList.add(item)
        }
        this.items.addAll(insertList)

        notifyDataSetChanged()
    }

    override fun setItems(items: List<T>){
        if(items.isEmpty()){
            return
        }
        this.items.clear()
        this.items.addAll(items)

        notifyDataSetChanged()
    }

    override fun updateItem(item: T) {
        val position = items.indexOf(item)

        if(0 <= position && position < items.size){
            items.removeAt(position)
            items.add(position, item)
            notifyItemChanged(position)
        }
    }

    override fun removeItem(item: T) {
        val position = items.indexOf(item)

        if(0 <= position && position < items.size){
            items.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    override fun removeItems() {
        this.items.clear()
        this.itemHashMap.clear()
    }

    override fun sort(comparator: Comparator<T>) {
        this.items.sortWith(comparator)
        notifyDataSetChanged()
    }

    private fun putItemHashMap(item : T){
        itemHashMap[item.hashCode()] = item
    }
}