package com.ncsuadc.marigold_android.domain

import java.util.UUID


data class User(
   var _id: UUID = UUID.randomUUID(),

   var clubs: List<Club> = listOf<Club>(),

   var email: String = "",

   var firstName: String = "",

   var graduationYear: Long = 0,

   var lastName: String = "",

   var notifications: List<Notification> = listOf<Notification>(),

   var profileImageUrl: String = "",

   var school: School? = null
)
