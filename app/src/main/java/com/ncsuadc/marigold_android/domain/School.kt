package com.ncsuadc.marigold_android.domain

import java.util.UUID

data class School(
    var _id: UUID = UUID.randomUUID(),
    var abbreviation: String = "",
    var clubDisplays: List<ClubDisplay> = listOf<ClubDisplay>(),
    var color: Long = 0,
    var fullName: String = "",
    var state: String = "",
    var type: String = ""
)
