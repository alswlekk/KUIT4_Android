package com.example.kuit4androidprivate.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface MenuCategoryDao {
    @Insert
    fun insert(menuCategoryData: MenuCategoryData)

    @Update
    fun update(menuCategoryData: MenuCategoryData)

    @Delete
    fun delete(menuCategoryData: MenuCategoryData)

    @Query("SELECT * FROM MenuCategoryData")
    fun getAll(): List<MenuCategoryData> // 가져오는 거니까 List로 받아야 함
}