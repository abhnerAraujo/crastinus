package com.bittya.crastinus

import android.net.Uri
import android.util.Log
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.bittya.crastinus.controllers.CategoryController
import com.bittya.crastinus.controllers.ToDoController
import com.bittya.crastinus.dtos.Category
import com.bittya.crastinus.dtos.ToDo
import com.bittya.crastinus.fragments.CategoryFragment
import com.bittya.crastinus.fragments.PreferencesFragment
import com.bittya.crastinus.fragments.ToDoFragment
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity :
        ToDoFragment.OnListFragmentInteractionListener,
        CategoryFragment.OnListFragmentInteractionListener,
        PreferencesFragment.OnFragmentInteractionListener,
        AppCompatActivity() {
    override fun onFragmentInteraction(uri: Uri) {
        Log.d("crastinus_navigation", "Preferences")
    }

    override fun onListFragmentInteraction(item: Category?) {
        Log.d("crastinus_click", item?.toString())
    }

    override fun onListFragmentInteraction(toDo: ToDo?) {
        Log.d("crastinus_click", toDo?.toString())
    }

    private val mFragmentManager = supportFragmentManager
    private var mLastTag = ""

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                message.setText(R.string.title_home)
                this.changeFragmentWithAnimations(main_frame.id,
                        ToDoFragment.newInstance(1),
                        "home")
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                message.setText(R.string.title_categories)
                this.changeFragmentWithAnimations(main_frame.id,
                        CategoryFragment.newInstance(1),
                        "cate")
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                message.setText(R.string.title_config)
                this.changeFragmentWithAnimations(main_frame.id,
                        PreferencesFragment.newInstance("azul","vermelho"),
                        "config")
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun changeFragmentWithAnimations(id: Int, fragment: Fragment, tag: String) {
        val animationMessage: Animation = AnimationUtils.loadAnimation(applicationContext,
                R.anim.fade_in_move_back)
        val animationFrame: Animation = AnimationUtils.loadAnimation(applicationContext,
                R.anim.grow_fade_in_from_bottom)
        val transaction = mFragmentManager.beginTransaction()
        if (this.mLastTag == "") {
            transaction
                    .add(id, fragment, tag)
                    .commit()
            message.startAnimation(animationMessage)
            main_frame.startAnimation(animationFrame)
            this.mLastTag = tag
        } else if (this.mLastTag != tag) {
            transaction
                    .replace(id, fragment, tag)
                    .commit()
            message.startAnimation(animationMessage)
            main_frame.startAnimation(animationFrame)
            this.mLastTag = tag
        }
    }

    fun plusOneToDo(view: View) {
        ToDoController()
                .saveTodo(ToDo("Kotlin language"
                        , Date()
                        , true
                        , Category.getDefaultCategory()))
    }

    fun getToDos(view: View) {
        val todos = ToDoController().getToDos()
        todos?.forEach {
            Log.d("crastinus_todo",
                    "title: ${it.description} - category: ${it.category.description}")
        }
    }

    fun getCategories(view: View) {
        val categories = CategoryController().getCategories()
        categories?.forEach { Log.d("crastinus_get", "category> ${it.description}") }
    }

    fun plusOneCategory(view: View) {
        CategoryController().saveCategory(Category("Aprender"))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (main_frame != null) {
            if (savedInstanceState != null) {
                return
            }
            this.changeFragmentWithAnimations(main_frame.id, ToDoFragment.newInstance(1), "home")
        }
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}
