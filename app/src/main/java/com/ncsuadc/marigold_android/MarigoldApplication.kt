package com.ncsuadc.marigold_android

import android.app.Application
import io.realm.kotlin.Realm
import io.realm.kotlin.internal.interop.RealmAppT
import io.realm.kotlin.mongodb.App
import io.realm.kotlin.mongodb.AppConfiguration

lateinit var app: App

class MarigoldApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // Initialization code here, e.g., Realm.init(this)

    }
}