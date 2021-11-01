package com.example.jpg_to_png_converter

import android.graphics.Bitmap

interface MainView {
    fun showPath(path: String)
    fun showProgress()
    fun hideProgress()
    fun showError()
    fun showSuccess()
    fun showCancellation()
}