package com.ncsuadc.marigold_android.domain

import io.realm.kotlin.types.RealmObject
import java.util.Date;

@RealmClass(embedded = true)
open class Post(
    var body: String = "",

    var postedAt: Date = Date(),

    var postedBy: User? = null,

    var title: String = ""
) : RealmObject() {}
