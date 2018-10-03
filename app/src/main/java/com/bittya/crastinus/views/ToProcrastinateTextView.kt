package com.bittya.crastinus.views

import android.content.Context
import android.os.Build
import android.util.TypedValue
import android.widget.TextView
import com.bittya.crastinus.R


class ToProcrastinateTextView(context: Context) : TextView(context) {

    init {
        if (Build.VERSION.SDK_INT >= 23) {
            this.setTextAppearance(R.style.HomeSectionTitle)
        } else {
            this.apply {
                setTextSize(TypedValue.COMPLEX_UNIT_SP, 14.toFloat())
            }
        }
    }

}