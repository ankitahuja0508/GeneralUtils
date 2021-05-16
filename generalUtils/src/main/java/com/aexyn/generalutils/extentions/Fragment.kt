package com.aexyn.generalutils.extentions

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.aexyn.generalutils.R
import com.google.android.material.snackbar.Snackbar

fun Fragment.showSnackbar(message:String, addAction:Boolean=false, actionText:String?=null, duration: Int = 3000){
    val snackbar = Snackbar.make(this.requireView(), message, if (addAction) Snackbar.LENGTH_INDEFINITE else duration)
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

fun Fragment.setUI(view: View){
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

fun Fragment.hideSoftKeyboard() {
    try {
        val inputMethodManager = this.requireActivity().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        this.requireActivity().currentFocus?.let {
            inputMethodManager.hideSoftInputFromWindow(this.requireActivity().currentFocus!!.applicationWindowToken, 0)
        } /*?: inputMethodManager.toggleSoftInput(
                InputMethodManager.HIDE_IMPLICIT_ONLY,0
        )*/
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun Fragment.showDeleteAlert(title:String, message:String, onCallback:(Boolean)-> Unit){
    val builder = AlertDialog.Builder(requireContext())
    builder.setTitle(title)
    builder.setMessage(message)

    builder.setPositiveButton(getString(R.string.yes)) { dialog, which ->
        onCallback(true)
    }
    builder.setNegativeButton(getString(R.string.no)) { dialog, which ->
        onCallback(false)
    }
    builder.show()
}

/*
private val ViewModel.navigationEventChannel: Channel<NavDirections>
    get() = Channel<NavDirections>()

val ViewModel.navigationEventEventFlow
    get() = this.navigationEventChannel.receiveAsFlow()

fun ViewModel.navigate(navDirections: NavDirections) = viewModelScope.launch {
    navigationEventChannel.send(navDirections)
}*/
