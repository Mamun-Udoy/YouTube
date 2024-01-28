package com.example.youtube.ui

import android.view.View

class DoubleClickListener(private val onDoubleClick: () -> Unit) : View.OnClickListener {
    private var lastClickTime: Long = 0
    private val doubleClickDelay = 1000 // Adjust this value as needed

    override fun onClick(v: View) {
        val clickTime = System.currentTimeMillis()

        if (clickTime - lastClickTime < doubleClickDelay) {
            // Double click detected
            onDoubleClick.invoke()
        }

        lastClickTime = clickTime
    }
}