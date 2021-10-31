package com.example.jpg_to_png_converter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jpg_to_png_converter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MainView {

    private lateinit var binding : ActivityMainBinding

    private val presenter = MainPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {

        }
    }

    override fun showProgress() {
        TODO("Not yet implemented")
    }

    override fun hideProgress() {
        TODO("Not yet implemented")
    }

    override fun showError() {
        TODO("Not yet implemented")
    }

    override fun showSuccess() {
        TODO("Not yet implemented")
    }
}