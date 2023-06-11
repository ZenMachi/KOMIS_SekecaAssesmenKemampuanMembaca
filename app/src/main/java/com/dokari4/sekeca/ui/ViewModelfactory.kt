package com.dokari4.sekeca.ui

import android.app.Application
import androidx.lifecycle.ViewModelProvider

class ViewModelfactory constructor(private val mApplication: Application) : ViewModelProvider.NewInstanceFactory() {
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
    override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(com.dokari4.sekeca.viewmodels.ViewModel::class.java)) {
            return com.dokari4.sekeca.viewmodels.ViewModel(mApplication) as T
        }
        throw IllegalArgumentException("Unknown Viewmodel Class: ${modelClass.name}")
    }
}