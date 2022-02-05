package com.nagwa.files.core.domain

/**
 * Data Class that represents the returned stream session response from the api
 *
 * Created by Muhammad Noamany
 * Email: muhammadnoamany@gmail.com
 */
data class StreamSessionModel(
    val id: Int = 0,
    val name: String = "",
    val sentBytes: Long = 0L,
)