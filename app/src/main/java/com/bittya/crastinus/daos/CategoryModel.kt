package com.bittya.crastinus.daos

import com.activeandroid.Model
import com.activeandroid.annotation.Column
import com.activeandroid.annotation.Table

@Table (name = "categories")
class CategoryModel() : Model() {

    @Column (name = "description")
    var description = ""

    constructor(description: String) : this() {
        this.description = description
    }
}