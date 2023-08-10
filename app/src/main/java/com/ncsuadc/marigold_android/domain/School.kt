package com.ncsuadc.marigold_android.domain

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

open class School(
    @PrimaryKey
    var _id: ObjectId = ObjectId(),

    var abbreviation: String = "",

    var clubs: RealmList<Club> = realmListOf(),

    var color: Long = 0,

    var fullName: String = "",

    var state: String = "",

    var type: String = ""
) : RealmObject {}
