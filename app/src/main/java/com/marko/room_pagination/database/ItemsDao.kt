package com.marko.room_pagination.database

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.marko.room_pagination.entities.Item

@Dao
interface ItemsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertItem(item: Item)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertItems(items: List<Item>)

    @Query("SELECT * FROM item ORDER BY id DESC")
    fun queryItems(): DataSource.Factory<Int, Item>
}