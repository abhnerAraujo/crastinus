package com.bittya.crastinus.daos

import com.activeandroid.Model
import com.activeandroid.annotation.Column
import com.activeandroid.annotation.Table
import com.bittya.crastinus.dtos.Category
import java.util.*

@Table(name = "todos")
open class ToDoModel() : Model() {

    @Column (name = "description") var description: String = ""
    @Column (name = "dueDate") var dueDate: Date? = null
    @Column (name = "starred") var starred: Boolean = false
    @Column (name = "category") var category = CategoryModel(Category.getDefaultCategory().description)

    constructor(description: String, dueDate: Date?, starred: Boolean, category: Category): this() {
        this.description = description
        this.dueDate = dueDate
        this.starred = starred
        this.category = CategoryModel(category.description)
    }
}
