package com.marko.room_pagination

import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.marko.room_pagination.entities.Item
import com.marko.room_pagination.extensions.inflate
import kotlinx.android.synthetic.main.list_item_item.view.*

class ItemsAdapter : PagedListAdapter<Item, ItemsAdapter.ItemHolder>(DiffCalbacks) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val view = parent.inflate(R.layout.list_item_item)
        return ItemHolder(view)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) =
        holder.bind(getItem(position)!!)

    inner class ItemHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(item: Item) {
            itemView.itemTitle.text = item.title
            itemView.itemSubtitle.text = item.subtitle
        }
    }

    private object DiffCalbacks : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean =
            oldItem == newItem
    }
}