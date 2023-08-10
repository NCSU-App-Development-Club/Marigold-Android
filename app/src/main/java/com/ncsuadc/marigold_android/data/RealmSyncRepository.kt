package com.ncsuadc.marigold_android.data

import com.ncsuadc.marigold_android.app
import com.ncsuadc.marigold_android.domain.Club
import com.ncsuadc.marigold_android.domain.Event
import com.ncsuadc.marigold_android.domain.Notification
import com.ncsuadc.marigold_android.domain.Post
import com.ncsuadc.marigold_android.domain.School
import com.ncsuadc.marigold_android.domain.User
import io.realm.kotlin.Realm
import io.realm.kotlin.mongodb.exceptions.SyncException
import io.realm.kotlin.mongodb.subscriptions
import io.realm.kotlin.mongodb.sync.SyncConfiguration
import io.realm.kotlin.mongodb.sync.SyncSession
import io.realm.kotlin.query.RealmResults
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.mongodb.kbson.ObjectId

interface SyncRepository {

    // Club methods
    fun getAllClubs(): RealmResults<Club>
    fun getClubById(id: ObjectId): Club?
    fun createOrUpdateClub(club: Club)
    fun deleteClub(club: Club)

    // Event methods
    fun getAllEvents(): RealmResults<Event>
    fun getEventById(id: ObjectId): Event?
    fun createOrUpdateEvent(event: Event)
    fun deleteEvent(event: Event)

    // Notification methods
    fun getAllNotifications(): RealmResults<Notification>
    fun getNotificationById(id: ObjectId): Notification?
    fun createOrUpdateNotification(notification: Notification)
    fun deleteNotification(notification: Notification)

    // Post methods
    fun getAllPosts(): RealmResults<Post>
    fun getPostById(id: ObjectId): Post?
    fun createOrUpdatePost(post: Post)
    fun deletePost(post: Post)

    // School methods
    fun getSchoolById(id: ObjectId): School?

    // User methods
    fun getAllUsers(): RealmResults<User>
    fun getUserById(id: ObjectId): User?
    fun createOrUpdateUser(user: User)
    fun deleteUser(user: User)

    // Clean up
    fun close()
}

class RealmSyncRepository(
    onSyncError: (session: SyncSession, error: SyncException) -> Unit
) : {

    private val realm: Realm
    private val config: SyncConfiguration
    private val currentUser: io.realm.kotlin.mongodb.User
        get() = app.currentUser!!

    init {
        config = SyncConfiguration.Builder(currentUser, setOf(Item::class))
            .initialSubscriptions { realm ->
                // Subscribe to the active subscriptionType - first time defaults to MINE
                val activeSubscriptionType = getActiveSubscriptionType(realm)
                add(getQuery(realm, activeSubscriptionType), activeSubscriptionType.name)
            }
            .errorHandler { session: SyncSession, error: SyncException ->
                onSyncError.invoke(session, error)
            }
            .waitForInitialRemoteData()
            .build()

        realm = Realm.open(config)

        // Mutable states must be updated on the UI thread
        CoroutineScope(Dispatchers.Main).launch {
            realm.subscriptions.waitForSynchronization()
        }
    }

}

