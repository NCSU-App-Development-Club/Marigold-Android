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
        app = App.create(
            AppConfiguration.Builder(getString(R.string.realm_app_id))
                .baseUrl(getString(R.string.realm_base_url))
                .build()
        )

        // Initialization code here, e.g., Realm.init(this)
    }
}