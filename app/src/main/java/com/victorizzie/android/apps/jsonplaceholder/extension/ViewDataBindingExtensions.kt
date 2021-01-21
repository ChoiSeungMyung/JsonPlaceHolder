package com.victorizzie.android.apps.jsonplaceholder.extension

import android.app.Activity
import android.view.inputmethod.InputMethodManager
import androidx.databinding.ViewDataBinding
import com.google.android.material.snackbar.Snackbar

fun ViewDataBinding.showSnackBar(id: Int, duration: Int = Snackbar.LENGTH_SHORT) {
    Snackbar.make(root, root.context.getString(id), duration).show()
}

fun ViewDataBinding.hideKeyboard() {
    (root.context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(
        root.windowToken,
        0
    )
}