package com.dokari4.sekeca.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dokari4.sekeca.huruf.HurufViewModel
import java.lang.IllegalArgumentException

class ViewModelfactory private constructor(private val mApplication: Application) : ViewModelProvider.NewInstanceFactory() {
    companion object {
        @Volatile
        private var INSTANCE: ViewModelfactory? = null

        @JvmStatic
        fun getInstance(application: Application): ViewModelfactory {
            if (INSTANCE == null) {
                synchronized(ViewModelfactory::class.java) {
                    INSTANCE = ViewModelfactory(application)
                }
            }
            return INSTANCE as ViewModelfactory
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HurufViewModel::class.java)) {
            return HurufViewModel(mApplication) as T
        }
        throw IllegalArgumentException("Unknown Viewmodel Class: ${modelClass.name}")
    }
}