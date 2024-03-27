package com.ncsuadc.marigold_android.domain

import java.util.UUID
import io.realm.kotlin.ext.realmListOf;
import io.realm.kotlin.types.RealmList;
import io.realm.kotlin.types.RealmObject;
import org.mongodb.kbson.ObjectId;

data class SchoolDisplay(
    var _id: UUID = UUID.randomUUID(),
    var abbreviation: String = "",
    var clubDisplays: List<ClubDisplay> = listOf<ClubDisplay>(),
    var color: Long = 0,
    var fullName: String = "",
    var state: String = "",
    var type: String = ""
)

class User : RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId()

    var clubs: RealmList<Club> = realmListOf()

    var email: String = ""

    var firstName: String = ""

    var graduationYear: Long = 0

    var lastName: String = ""

    var notifications: RealmList<Notification> = realmListOf()

    var profileImageUrl: String = ""

    var school: School? = null
}

