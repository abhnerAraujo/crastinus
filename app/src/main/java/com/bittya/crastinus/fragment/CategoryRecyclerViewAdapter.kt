package com.bittya.crastinus.fragment

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bittya.crastinus.R
import com.bittya.crastinus.dtos.Category

import com.bittya.crastinus.fragment.CategoryFragment.OnListFragmentInteractionListener

import kotlinx.android.synthetic.main.fragment_category.view.*

/**
 * [RecyclerView.Adapter] that can display a [Category] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 */
class CategoryRecyclerViewAdapter(
        private val mValues: List<Category>?,
        private val mListener: OnListFragmentInteractionListener?)
    : RecyclerView.Adapter<CategoryRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as Category
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
            mListener?.onListFragmentInteraction(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_category, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(mValues != null){
            val item = mValues[position]
            holder.mContentView.text = item.description

            with(holder.mView) {
                tag = item
                setOnClickListener(mOnClickListener)
            }
        }
    }

    override fun getItemCount(): Int = mValues?.size ?: 0

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mContentView: TextView = mView.content

        override fun toString(): String {
            return super.toString() + " '" + mContentView.text + "'"
        }
    }
}
