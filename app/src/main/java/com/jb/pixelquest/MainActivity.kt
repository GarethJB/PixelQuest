package com.jb.pixelquest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import com.jb.pixelquest.presentation.navigation.BottomNavigationBar
import com.jb.pixelquest.presentation.navigation.NavGraph

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    
                    Column(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        NavGraph(
                            navController = navController,
                            modifier = Modifier.weight(1f)
                        )
                        BottomNavigationBar(navController = navController)
                    }
                }
            }
        }
    }
}


