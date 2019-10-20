package com.marko.room_pagination.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Item(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val subtitle: String
)