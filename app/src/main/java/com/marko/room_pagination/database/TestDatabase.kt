package com.marko.room_pagination.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.marko.room_pagination.entities.Item

@Database(entities = [Item::class], version = 1)
abstract class TestDatabase : RoomDatabase() {

    abstract fun itemsDao(): ItemsDao

    companion object {
        private var instance: TestDatabase? = null

        fun getInstance(context: Context): TestDatabase = synchronized(Any()) {
            if (instance != null) {
                instance!!
            } else {
                instance = Room.databaseBuilder(context, TestDatabase::class.java, "test_db.db")
                    .build()
                instance!!
            }
        }
    }
}