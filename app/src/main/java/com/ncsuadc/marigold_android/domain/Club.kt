package com.ncsuadc.marigold_android.domain

import java.util.UUID

data class Club(
    var _id: UUID = UUID.randomUUID(),
    var color: Long = 0,
    var events: List<EventDisplay> = listOf<EventDisplay>(),
    var fullName: String = "",
    var imageUrl: String = "",
    var members: List<User> = listOf<User>(),
    var posts: List<Post> = listOf<Post>(),
    var school: School? = null,
    var shortName: String = ""
)

