package com.ncsuadc.marigold_android.domain
import io.realm.kotlin.types.EmbeddedRealmObject;
import io.realm.kotlin.types.RealmInstant;
import java.util.Date

data class NotificationDisplay(
    var body: String = "",
    var clubDisplay: ClubDisplay? = null,
    var from: User? = null,
    var isSystemNotification: Boolean = false,
    var sentAt: Date = Date(),
    var title: String = ""
)

class Notification : EmbeddedRealmObject {
    var body: String = ""

    var club: Club? = null

    var from: User? = null

    var isSystemNotification: Boolean = false

    var sentAt: RealmInstant = RealmInstant.now()

    var title: String = ""
}
