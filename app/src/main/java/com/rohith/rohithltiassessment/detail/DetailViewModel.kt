package com.rohith.rohithltiassessment.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rohith.rohithltiassessment.network.Source

/**
 * The [ViewModel] that is associated with the [DetailFragment].
 */
class DetailViewModel(@Suppress("UNUSED_PARAMETER")source: Source, app: Application) : AndroidViewModel(app) {

    // The internal MutableLiveData for the selected article
    private val _selectedSource = MutableLiveData<Source>()

    // The external LiveData for the Selectedarticle
    val selectedarticle: LiveData<Source>
        get() = _selectedSource

    // Initialize the _selectedarticle MutableLiveData
    init {
        _selectedSource.value = source
    }
}
