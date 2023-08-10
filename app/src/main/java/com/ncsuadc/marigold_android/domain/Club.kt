package com.ncsuadc.marigold_android.domain

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

open class Club(
    @PrimaryKey var _id: ObjectId = ObjectId(),

    var color: Long = 0,

    var events: RealmList<Event> = realmListOf(),

    var fullName: String = "",

    var imageUrl: String = "",

    var members: RealmList<User> = realmListOf(),

    var posts: RealmList<Post> = realmListOf(),

    var school: School? = null,

    var shortName: String = ""
) : RealmObject {}
