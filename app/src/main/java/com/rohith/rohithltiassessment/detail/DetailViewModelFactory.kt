package com.rohith.rohithltiassessment.detail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rohith.rohithltiassessment.network.Source

/**
 * Simple ViewModel factory that provides the Source and context to the ViewModel.
 */
class DetailViewModelFactory(
    private val source: Source,
    private val application: Application) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(source, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
