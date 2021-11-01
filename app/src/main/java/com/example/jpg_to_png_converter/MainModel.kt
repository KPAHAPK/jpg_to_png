package com.example.jpg_to_png_converter

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Environment
import io.reactivex.rxjava3.disposables.CompositeDisposable
import java.io.File
import java.io.FileOutputStream
import java.lang.StringBuilder
import java.text.SimpleDateFormat
import java.util.*

class MainModel {

    private val disposable = CompositeDisposable()

    lateinit var context: Context
    lateinit var newPNGFile: File

    fun getBitmap(data: Intent?): Bitmap {
        val uri = data?.data
        return BitmapFactory.decodeStream(uri?.let { context.contentResolver.openInputStream(it) })
    }

    fun saveAsPNGToStorage(bitmap: Bitmap) {
        val dir = createDir()
        newPNGFile = createNewFile(dir)
        convertToPNG(newPNGFile, bitmap)
    }

    private fun convertToPNG(newPNGFile: File, bitmap: Bitmap) {
        val out = FileOutputStream(newPNGFile)
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)
        out.close()
    }

    private fun createNewFile(dir: String): File {
        return File(
            dir + File.separator + StringBuilder()
                .append(
                    SimpleDateFormat("dd-MM-yyyy_hh_mm_ss", Locale.ENGLISH)
                        .format(Date())
                )
                .append(".png")
        )
    }

    private fun createDir(): String {
        val dir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES).toString() + "/PNG"
        val outputDir = File(dir)
        outputDir.mkdirs()
        return dir
    }

}