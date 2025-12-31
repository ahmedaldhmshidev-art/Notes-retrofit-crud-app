package com.example.retrofit_crud_just_internet.Model


import android.os.SystemClock
import android.view.View

fun View.setSafeOnClickListener(interval: Long = 500, onSafeClick: (View) -> Unit) {
    var lastClickTime = 0L
    setOnClickListener {
        val now = SystemClock.elapsedRealtime()
        if (now - lastClickTime < interval) return@setOnClickListener
        lastClickTime = now
        onSafeClick(it)
    }
}
