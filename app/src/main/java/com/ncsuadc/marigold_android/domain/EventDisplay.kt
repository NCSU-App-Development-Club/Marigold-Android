package com.ncsuadc.marigold_android.domain

import java.util.Date
import java.util.UUID
import io.realm.kotlin.ext.realmListOf;
import io.realm.kotlin.types.RealmInstant;
import io.realm.kotlin.types.RealmList;
import io.realm.kotlin.types.RealmObject;
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId;
data class EventDisplay(
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

class Event : RealmObject {
   @PrimaryKey
   var _id: ObjectId = ObjectId()

   var attending: RealmList<User> = realmListOf()

   var body: String = ""

   var club: Club? = null

   var date: RealmInstant = RealmInstant.now()

   var isPrivate: Boolean = false

   var location: String = ""

   var postedAt: RealmInstant = RealmInstant.now()

   var postedBy: User? = null

   var title: String = ""
}


