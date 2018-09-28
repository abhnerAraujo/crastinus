package com.bittya.crastinus.controllers

import android.util.Log
import com.activeandroid.query.Select
import com.bittya.crastinus.daos.CategoryModel
import com.bittya.crastinus.dtos.Category

class CategoryController {

    fun saveCategory(category: Category): Long {
        var id: Long = 0
        try {
            id = CategoryModel(category.description).save()
            Log.d("crastinus_save", "Saved with id> $id")
        } catch (e: Exception) {
            e.let { Log.d("crastinus_error", it.message) }
            return id
        }
        return id
    }

    fun getCategories() : List<Category>? {
        val values = mutableListOf<Category>()
        try {
            val categories = Select()
                    .from(CategoryModel::class.java)
                    .execute<CategoryModel>()
            if(!categories.isEmpty()) {
                categories.forEach { values.add(Category(it.description)) }
            }
        } catch (e: Exception) {
            Log.d("crastinus_error", e.message)
        } finally {
            return values
        }
    }

}