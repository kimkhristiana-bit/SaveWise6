package com.kim.savewise

import android.content.Context
import com.cloudinary.android.MediaManager

object CloudinaryConfig {
    // Replace these with your actual Cloudinary details
    const val CLOUD_NAME = "dknnaj1yc"
    const val API_KEY = "967185535386265"
    const val API_SECRET = "y4cBFu4Un3RWsHmiVmHenyua-6I"

    fun initialize(context: Context) {
        val config = mapOf(
            "cloud_name" to CLOUD_NAME,
            "api_key" to API_KEY,
            "api_secret" to API_SECRET
        )
        try {
            MediaManager.init(context, config)
        } catch (e: Exception) {
            // Already initialized or other error
        }
    }
}
