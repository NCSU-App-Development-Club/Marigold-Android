package com.ncsuadc.marigold_android.domain

import io.realm.kotlin.types.EmbeddedRealmObject
import java.util.Date

open class Notification(
    var body: String = "",

    var club: Club? = null,

    var from: User? = null,

    var isSystemNotification: Boolean = false,

    var sentAt: Date = Date(),

    var title: String = ""
) : EmbeddedRealmObject {}
