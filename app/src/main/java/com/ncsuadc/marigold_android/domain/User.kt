package com.ncsuadc.marigold_android.domain


import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

open class User(
    @PrimaryKey
    var _id: ObjectId = ObjectId(),

    var clubs: RealmList<Club> = realmListOf(),

    var email: String = "",

    var firstName: String = "",

    var graduationYear: Long = 0,

    var lastName: String = "",

    var notifications: RealmList<Notification> = realmListOf(),

    var profileImageUrl: String = "",

    var school: School? = null
) : RealmObject {}
