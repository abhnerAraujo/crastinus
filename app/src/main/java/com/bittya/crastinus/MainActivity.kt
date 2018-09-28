package com.bittya.crastinus

import android.util.Log
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.transition.Scene
import android.support.transition.Transition
import android.support.transition.TransitionInflater
import android.support.v4.app.Fragment
import android.support.v4.view.ViewGroupCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.bittya.crastinus.controllers.CategoryController
import com.bittya.crastinus.controllers.ToDoController
import com.bittya.crastinus.dtos.Category
import com.bittya.crastinus.dtos.ToDo
import com.bittya.crastinus.fragment.CategoryFragment
import com.bittya.crastinus.fragment.PreferencesFragment
import com.bittya.crastinus.fragment.ToDoFragment
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity :
        ToDoFragment.OnListFragmentInteractionListener,
        CategoryFragment.OnListFragmentInteractionListener,
        AppCompatActivity() {
    override fun onListFragmentInteraction(item: Category?) {
        Log.d("crastinus_click", item?.toString())
    }

    override fun onListFragmentInteraction(toDo: ToDo?) {
        Log.d("crastinus_click", toDo?.toString())
    }

    private var mSceneRoot: ViewGroup = FrameLayout(this)
    private var mSceneTodo: Scene? = null
    private var mSceneCategory: Scene? = null
    private val mFragmentManager = supportFragmentManager
    private var mFadeTransition: Transition? = null
    private var mLastTag = ""

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                message.setText(R.string.title_home)
                this.changeFragment(main_frame.id,
                        ToDoFragment.newInstance(1),
                        "home")
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                message.setText(R.string.title_categories)
                this.changeFragment(main_frame.id,
                        CategoryFragment.newInstance(1),
                        "cate")
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                message.setText(R.string.title_config)
                this.changeFragment(main_frame.id,
                        PreferencesFragment.newInstance("azul","vermelho"),
                        "config")
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun changeFragment(id: Int, fragment: Fragment, tag: String) {
        if (this.mLastTag == "") {
            this.mFragmentManager.beginTransaction()
                    .add(id, fragment, tag)
                    .commit()
            this.mLastTag = tag
        } else if (this.mLastTag != tag) {
            this.mFragmentManager.beginTransaction()
                    .replace(id, fragment, tag)
                    .commit()
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
        this.mSceneRoot = findViewById<FrameLayout>(main_frame.id)
        this.mSceneTodo = Scene.getSceneForLayout(mSceneRoot,
                R.layout.fragment_todo_list,
                this)
        this.mSceneCategory = Scene.getSceneForLayout(mSceneRoot,
                R.layout.fragment_category_list,
                this)
        mFadeTransition = TransitionInflater.from(this)
                .inflateTransition(R.transition.fade_transition)
        if (main_frame != null) {
            if (savedInstanceState != null) {
                return
            }
            this.changeFragment(main_frame.id, ToDoFragment.newInstance(1), "home")
        }
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
}
