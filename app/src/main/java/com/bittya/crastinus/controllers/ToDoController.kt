package com.bittya.crastinus.controllers

import android.util.Log
import com.activeandroid.query.Select
import com.bittya.crastinus.daos.ToDoModel
import com.bittya.crastinus.dtos.Category
import com.bittya.crastinus.dtos.ToDo

class ToDoController {

    fun saveTodo(todo: ToDo) : Long {
        var id: Long = 0
        try {
            id = ToDoModel(todo.description,
                    todo.dueDate,
                    todo.starred,
                    todo.category)
                    .save()
            Log.d("crastinus_save", "ToDo saved with id> $id")
        } catch (e: Exception) {
            Log.d("crastinus_error", e.message)
        } finally {
            return id
        }
    }

    fun getToDos() : List<ToDo>? {
        val values = mutableListOf<ToDo>()
        try {
            val todos = Select()
                    .from(ToDoModel::class.java)
                    .execute<ToDoModel>()
            if(!todos.isEmpty()) { todos.forEach {
                values.add(ToDo(it.description,
                        it.dueDate,
                        it.starred,
                        Category(it.category.description))) }
            }
        } catch (e : Exception) {
            Log.d("crastinus_error", e.message)
        } finally {
            return values
        }
    }


}