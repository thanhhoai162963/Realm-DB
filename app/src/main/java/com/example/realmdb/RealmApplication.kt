package com.example.realmdb

import android.app.Application
import io.realm.Realm
import io.realm.Realm.init
import io.realm.RealmConfiguration


class RealmApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)

        val config: RealmConfiguration = RealmConfiguration.Builder()
            .name("student.realm")
            .schemaVersion(1)
            .deleteRealmIfMigrationNeeded()
            .build()

        Realm.setDefaultConfiguration(config)
    }
}