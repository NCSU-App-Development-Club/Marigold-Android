package com.ncsuadc.marigold_android.domain

import java.util.Date
import io.realm.kotlin.types.EmbeddedRealmObject;
import io.realm.kotlin.types.RealmInstant;

data class PostDisplay(
    var body: String = "",
    var postedAt: Date = Date(),
    var postedBy: User? = null,
    var title: String = ""
)

class Post : EmbeddedRealmObject {
    var body: String = ""

    var postedAt: RealmInstant = RealmInstant.now()

    var postedBy: User? = null

    var title: String = ""
}
