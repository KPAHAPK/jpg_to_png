package com.example.jpg_to_png_converter

interface MainView {
    fun showProgress()
    fun hideProgress()
    fun showError()
    fun showSuccess()
}