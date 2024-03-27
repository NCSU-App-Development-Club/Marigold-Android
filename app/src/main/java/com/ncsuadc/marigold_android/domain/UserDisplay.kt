package com.ncsuadc.marigold_android.domain

import java.util.UUID
import io.realm.kotlin.ext.realmListOf;
import io.realm.kotlin.types.RealmList;
import io.realm.kotlin.types.RealmObject;
import org.mongodb.kbson.ObjectId;

data class UserDisplay(
    var _id: UUID = UUID.randomUUID(),

    var clubDisplays: List<ClubDisplay> = listOf<ClubDisplay>(),

    var email: String = "",

    var firstName: String = "",

    var graduationYear: Long = 0,

    var lastName: String = "",

    var notifications: List<Notification> = listOf<Notification>(),

    var profileImageUrl: String = "",

    var school: School? = null
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
