package com.alphaapps.peoplecerttask.constants

/**
 * Created by Muhammad Noamany
 * Email: muhammadnoamany@gmail.com
 */
object Constants {
    // Frame capture interval of live camera
    const val CAPTURE_INTERVAL = 1000

    // Requirements for Agora streaming
    const val AGORA_APP_ID = "6a55356c63f341da8da0dd5a0be35e16"
    const val AGORA_CHANNEL = "testStream"
    const val AGORA_TOKEN =
        "0066a55356c63f341da8da0dd5a0be35e16IAButp/BjZBOOJvWE7d7ufWFSEflNX+DYgMGTWwdEddApL3xo3YAAAAAEABJ0h4Ow9D/YQEAAQDD0P9h"


    /**
     * Enum to represent the Degrees of device screen orientation
     */
    enum class ScreenOrientation(var value: Int) {
        ORIENTATION_270_DEGREE(270), ORIENTATION_180_DEGREE(180), ORIENTATION_90_DEGREE(90), ORIENTATION_ZERO_DEGREE(
            0
        )
    }
}