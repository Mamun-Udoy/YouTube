package com.example.youtube.utils

import android.view.View
import androidx.navigation.NavController

fun NavController.navigateTo(destinationResId: Int) {

    if (currentDestination == null) {
        navigate(destinationResId)
    } else {
        currentDestination?.let {
            if (it.id != destinationResId) {
                navigate(destinationResId)
            }
        }
    }
}

infix fun View.onClick(function: (View) -> Unit) {
    setOnClickListener {
        function.invoke(it)
    }
}