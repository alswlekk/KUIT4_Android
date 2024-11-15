package com.example.kuit4androidprivate.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MenuData::class], version = 1)
abstract class MenuLatelyDB : RoomDatabase() {
    abstract fun menuLatelyDao(): MenuLatelyDao

    companion object {
        private var instance: MenuLatelyDB? = null

        @Synchronized
        fun getInstance(context: Context): MenuLatelyDB {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context,
                    MenuLatelyDB::class.java,
                    "menu_lately_db"
                ).build()
            }
            return instance!!
        }
    }
}
