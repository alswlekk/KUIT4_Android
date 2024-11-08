package com.example.kuit4androidprivate.model



data class MenuData(

    val imageUrl : String? = null,
    val title: String,
    val time: String,
    val review: String,
    val count : String,
    val imgId: Int? = null,
    var bookmark: Boolean? = false

)
