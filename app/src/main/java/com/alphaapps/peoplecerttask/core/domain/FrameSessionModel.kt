package com.nagwa.files.core.domain

/**
 * Data Class that represents the returned frames session response from the api
 *
 * Created by Muhammad Noamany
 * Email: muhammadnoamany@gmail.com
 */
data class FrameSessionModel(
    val id: Int = 0,
    val name: String = "",
    val framesSent: Int = 0,
)