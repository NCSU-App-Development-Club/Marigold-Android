package com.ncsuadc.marigold_android.data

import com.ncsuadc.marigold_android.app
import io.realm.kotlin.mongodb.Credentials

/**
 * Repository allowing users to create accounts or log in to the app with an existing account.
 */
interface AuthRepository {
    /**
     * Creates an account with the specified [email] and [password].
     */
    suspend fun createAccount(email: String, password: String)

    /**
     * Logs in with the specified [email] and [password].
     */
    suspend fun login(email: String, password: String)
}

/**
 * [AuthRepository] for authenticating with MongoDB.
 */
object RealmAuthRepository : AuthRepository {
    override suspend fun createAccount(email: String, password: String) {
        app.emailPasswordAuth.registerUser(email, password)
    }

    override suspend fun login(email: String, password: String) {
        app.login(Credentials.emailPassword(email, password))
    }
}