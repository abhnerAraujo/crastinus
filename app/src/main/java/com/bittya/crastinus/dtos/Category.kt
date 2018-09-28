package com.bittya.crastinus.dtos

class Category (
        var description: String) {
    companion object {
        private const val DEFAULT_CATEGORY_NAME = "Procrastinate"
        fun getDefaultCategory() : Category = Category(DEFAULT_CATEGORY_NAME)
    }
}