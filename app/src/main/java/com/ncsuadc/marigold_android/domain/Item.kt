package com.ncsuadc.marigold_android.domain

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

open class Item(
    @PrimaryKey
    var _id: ObjectId = ObjectId(),

    var isComplete: Boolean = false,

    var owner_id: String = "",

    var summary: String = ""
) : RealmObject {}
