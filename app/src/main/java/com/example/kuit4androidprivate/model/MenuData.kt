package com.example.kuit4androidprivate.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class MenuData(

    val imageUrl: String? = null,
    val title: String,
    val time: String,
    val review: String,
    val count: String,
    val imgId: Int? = null,
    var bookmark: Boolean? = false

) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
