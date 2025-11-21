package com.shahtott.shoee_store_compose.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.shahtott.shoee_store_compose.ui.screens.home.HomeScreen
import com.shahtott.shoee_store_compose.ui.screens.login.LoginScreen
import com.shahtott.shoee_store_compose.ui.screens.register.RegisterScreen
import com.shahtott.shoee_store_compose.ui.screens.splash.SplashScreen

@Composable
fun Navigation(startDestination: Destination = Destination.Splash) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination) {
        composable<Destination.Splash> {
            SplashScreen {
                navController.navigate(Destination.Login) {
                    popUpTo<Destination.Splash> { inclusive = true }
                }
            }
        }
        composable<Destination.Login> {
            LoginScreen(onLoginClicked = {
                navController.navigate(Destination.Home)
            }, onRegisterClicked = {
                navController.navigate(Destination.Register)
            })
        }
        composable<Destination.Register> {
            RegisterScreen {
                navController.popBackStack()
            }
        }
        composable<Destination.Home> {
            HomeScreen()
        }
    }
}