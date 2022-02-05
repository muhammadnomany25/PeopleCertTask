package com.alphaapps.peoplecerttask.presentation.base

import androidx.lifecycle.MutableLiveData

/**
 * Created by Muhammad Noamany
 * Email: muhammadnoamany@gmail.com
 */


/**
 * Used to save livedata and it state (Success, Error, Loading)
 */
data class BaseUiModel<T>(
    var filesData: MutableLiveData<List<T>> = MutableLiveData<List<T>>(),
    var status: MutableLiveData<Int> = MutableLiveData()
)

/**
 * Enum class that represents the different status of requests
 */
enum class Status(var value: Int) {
    LOADING(1), SUCCESS(2), ERROR(3)
}
