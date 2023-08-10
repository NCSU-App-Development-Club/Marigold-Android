package com.ncsuadc.marigold_android.domain


import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId
import java.util.Date

open class Event(
    @PrimaryKey
    var _id: ObjectId = ObjectId(),

    var attending: RealmList<User> = realmListOf<User>(),

    var body: String = "",

    var club: Club? = null,

    var date: Date = Date(),

    var location: String = "",

    var postedAt: Date = Date(),

    var postedBy: User? = null,

    var title: String = ""
) : RealmObject {}
