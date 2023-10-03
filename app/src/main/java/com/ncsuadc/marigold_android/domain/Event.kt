package com.ncsuadc.marigold_android.domain

import java.util.Date
import java.util.UUID

data class Event(
   var _id: UUID = UUID.randomUUID(),
   var attending: List<User> = listOf<User>(),
   var body: String = "",
   var club: Club? = null,
   var date: Date = Date(),
   var isPrivate: Boolean = false,
   var location: String = "",
   var postedAt: Date = Date(),
   var postedBy: User? = null,
   var title: String = ""
)

