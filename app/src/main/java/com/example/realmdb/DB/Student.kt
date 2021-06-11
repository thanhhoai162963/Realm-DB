package com.example.realmdb.DB

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required

open class Student : RealmObject() {

    @PrimaryKey
    var id:Long? = 0
    var name:String? = ""
    var email:String? = ""


}