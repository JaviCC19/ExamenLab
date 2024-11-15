package com.uvg.javiercc.labclase12.data.Local

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [AssetEntity::class],
    version = 1
)
abstract class Database: RoomDatabase() {
    abstract fun AssetDao(): AssetDao
}