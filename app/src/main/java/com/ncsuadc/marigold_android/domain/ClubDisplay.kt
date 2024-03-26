package com.ncsuadc.marigold_android.domain

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId
import java.util.UUID


data class ClubDisplay (
    var _id: UUID = UUID.randomUUID(),
    var color: Long = 0,
    var events: List<EventDTO> = listOf<EventDisplay>(),
    var fullName: String = "",
    var imageUrl: String = "",
    var members: List<UserDTO> = listOf<UserDisplay>(),
    var posts: List<PostDTO> = listOf<PostDisplay>(),
    var school: SchoolDTO? = null,
    var shortName: String = ""
)


class Club : RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId()

    var color: Long = 0

    var events: RealmList<Event> = realmListOf()

    var fullName: String = ""

    var imageUrl: String = ""

    var members: RealmList<User> = realmListOf()

    var posts: RealmList<Post> = realmListOf()

    var school: School? = null

    var shortName: String = ""
}
