package com.mindera.rocketscience.featue_demo.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mindera.rocketscience.featue_demo.presentation.home.HomeScreen
import com.mindera.rocketscience.featue_demo.util.Screen
import com.mindera.rocketscience.ui.theme.RocketScienceTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RocketScienceTheme {
                Surface {
                    val navController = rememberNavController()
                    NavHost(modifier = Modifier.padding(vertical = 20.dp), navController = navController, startDestination = Screen.HomeScreen.route){
                        composable(
                            route = Screen.HomeScreen.route
                        ){
                            HomeScreen()
                        }
                    }
                }
            }
        }
    }
}
