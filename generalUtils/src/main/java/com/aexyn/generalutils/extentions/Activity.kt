package com.aexyn.generalutils.extentions

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import com.aexyn.generalutils.R
import com.google.android.material.snackbar.Snackbar

fun Activity.showSnackbar(view: View, message:String, addAction:Boolean=false, actionText:String?=null, duration: Int = 3000){
    val snackbar = Snackbar.make(view, message, if (addAction) Snackbar.LENGTH_INDEFINITE else duration)
    val snackbarView: View = snackbar.view
    snackbarView.elevation = 50.0f
    val textView =
            snackbarView.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
    textView.maxLines = 10

    if(addAction){
        snackbar.setAction(actionText ?: getString(R.string.okay), View.OnClickListener {
            snackbar.dismiss()
        })
    }
    snackbar.show()
}

fun Activity.showSoftKeyboard(view: View) {
    try {
        val inputMethodManager =
                this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        inputMethodManager!!.showSoftInput(
                view,
                InputMethodManager.SHOW_FORCED
        )
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun Activity.setUI(view: View){
    if (view !is EditText) {
        view.setOnTouchListener { _, _ ->
            this.hideSoftKeyboard()
            false
        }
    }
    //If a layout container, iterate over children and seed recursion.
    if (view is ViewGroup) {
        for (i in 0 until view.childCount) {
            val innerView = view.getChildAt(i)
            setUI(innerView)
        }
    }
}

fun Activity.hideSoftKeyboard() {
    try {
        val inputMethodManager = this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(this.currentFocus!!.applicationWindowToken, 0)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}