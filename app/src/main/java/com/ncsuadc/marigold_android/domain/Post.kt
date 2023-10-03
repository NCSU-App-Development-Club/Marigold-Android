package com.ncsuadc.marigold_android.domain

import java.util.Date

data class Post(
    var body: String = "",
    var postedAt: Date = Date(),
    var postedBy: User? = null,
    var title: String = ""
)
