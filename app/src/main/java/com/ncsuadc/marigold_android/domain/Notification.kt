package com.ncsuadc.marigold_android.domain

import java.util.Date

data class Notification(
    var body: String = "",
    var clubDisplay: ClubDisplay? = null,
    var from: User? = null,
    var isSystemNotification: Boolean = false,
    var sentAt: Date = Date(),
    var title: String = ""
)