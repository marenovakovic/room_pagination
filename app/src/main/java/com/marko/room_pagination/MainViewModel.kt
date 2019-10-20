package com.marko.room_pagination

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.marko.room_pagination.database.TestDatabase
import com.marko.room_pagination.entities.Item
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val database = TestDatabase.getInstance(application)

    init {
        val prePopulatedItems = mutableListOf<Item>()
        repeat(30) {
            prePopulatedItems += Item(it + 1, "title", "subtitle")
        }

        viewModelScope.launch(Dispatchers.IO) {
            database.itemsDao().insertItems(prePopulatedItems)
        }
    }

    val items: LiveData<PagedList<Item>> by lazy {
        val dataSource = database.itemsDao().queryItems()
        val config = PagedList.Config.Builder()
            .setPageSize(10)
            .build()
        LivePagedListBuilder<Int, Item>(dataSource, config).build()
    }

    fun insertItemOnStart() {
        viewModelScope.launch(Dispatchers.IO) {
            val item = Item(0, "0", "0")
            database.itemsDao().insertItem(item)
        }
    }
}