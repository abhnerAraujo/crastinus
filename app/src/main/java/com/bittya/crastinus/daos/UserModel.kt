package com.bittya.crastinus.daos

import com.activeandroid.Model
import com.activeandroid.annotation.Column
import com.activeandroid.annotation.Table

@Table (name = "users")
class UserModel (
        @Column (name = "full_name") var fullname: String,
        @Column (name = "email") var email: String,
        @Column (name = "birth_date") var birthDate: String,
        @Column (name = "sex") var sex: String) : Model() {}