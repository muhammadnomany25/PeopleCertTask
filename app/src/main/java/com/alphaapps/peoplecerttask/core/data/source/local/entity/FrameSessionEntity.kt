package com.alphaapps.peoplecerttask.core.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Data Class that represents the entity object to be in the local database structure
 *
 * Created by Muhammad Noamany
 * Email: muhammadnoamany@gmail.com
 */
@Entity(tableName = "FrameSession")
data class FrameSessionEntity(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    val sessionName: String = "",
    var framesSent: Int = 0
)