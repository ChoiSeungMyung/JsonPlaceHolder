package com.victorizzie.android.apps.jsonplaceholder.extension

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.showSnackBar(id: Int, duration: Int = Snackbar.LENGTH_SHORT) {
    Snackbar.make(this, context.getString(id), duration).show()
}