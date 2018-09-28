package com.bittya.crastinus.dtos

import java.util.*

class ToDo (var description: String,
            var dueDate: Date?,
            var starred: Boolean,
            var category: Category) {
    init {
        category = Category.getDefaultCategory()
    }
}