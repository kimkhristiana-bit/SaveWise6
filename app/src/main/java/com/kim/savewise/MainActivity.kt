package com.kim.savewise

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.kim.savewise.ui.theme.SaveWiseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Initialize Cloudinary with your details in CloudinaryConfig.kt
        CloudinaryConfig.initialize(this)
        
        enableEdgeToEdge()
        setContent {
            SaveWiseTheme {
                val navController = rememberNavController()
                NavGraph(navController = navController)
            }
        }
    }
}
