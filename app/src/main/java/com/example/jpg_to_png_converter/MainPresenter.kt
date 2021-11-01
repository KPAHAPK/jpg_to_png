package com.example.jpg_to_png_converter

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.observers.DisposableObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import java.lang.Thread.sleep

class MainPresenter(private val mainView: MainView) {

    private val model = MainModel()
    lateinit var disposable: Disposable

    fun setContext(context: Context) {
        model.context = context
    }

    fun saveAsPNG(data: Intent) {
        mainView.showProgress()
        val observable = Observable.just(data)
        disposable = observable
            .subscribeOn(Schedulers.newThread())
            .map { sleep(2000)
                model.getBitmap(it) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<Bitmap>() {
                override fun onNext(t: Bitmap) {
                    model.saveAsPNGToStorage(t)
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                }

                override fun onComplete() {
                    mainView.showPath(model.newPNGFile.absolutePath)
                    mainView.showSuccess()
                }
            })
    }

    fun cancelConversion() {
        disposable.dispose()
        mainView.showCancellation()
    }


}