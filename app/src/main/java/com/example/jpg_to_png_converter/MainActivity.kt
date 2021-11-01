package com.example.jpg_to_png_converter

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.jpg_to_png_converter.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity(), MainView {

    private lateinit var binding : ActivityMainBinding

    private lateinit var dialog : MaterialAlertDialogBuilder

    private val presenter = MainPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.convertAnImageBtn.setOnClickListener {
            selectImageToConvert()
        }
    }

    private fun selectImageToConvert(){
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, 0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK){
            presenter.setContext(this)
            presenter.saveAsPNG(data!!)
        }
    }

    override fun showPath(path: String) {
        binding.pathTextView.text = path
    }

    override fun showProgress() {
        dialog = MaterialAlertDialogBuilder(this)
        dialog
            .setTitle("Конвертация...")
            .setMessage("Прогресс...")
            .setNeutralButton("Ok") { d, _ -> d.dismiss()}
            .setNegativeButton("Cancel") { _,_ ->
                presenter.cancelConversion()
            }
            .create()
            .show()
    }

    override fun hideProgress() {
    }

    override fun showError() {
        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
    }

    override fun showSuccess() {
        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
    }

    override fun showCancellation() {
        Toast.makeText(this, "Canceled", Toast.LENGTH_SHORT).show()
    }
}