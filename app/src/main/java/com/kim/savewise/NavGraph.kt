package com.kim.savewise

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Register : Screen("register")
    object Login : Screen("login")
    object Dashboard : Screen("dashboard")
    object SavingsHub : Screen("savings_hub")
    object Rewards : Screen("rewards")
    object Profile : Screen("profile")
}

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(
                onNavigateToRegister = { navController.navigate(Screen.Register.route) },
                onNavigateToLogin = { navController.navigate(Screen.Login.route) }
            )
        }
        composable(Screen.Register.route) {
            RegisterScreen(
                onBack = { navController.popBackStack() },
                onRegisterSuccess = { 
                    navController.navigate(Screen.Dashboard.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                },
                onNavigateToLogin = { navController.navigate(Screen.Login.route) }
            )
        }
        composable(Screen.Login.route) {
            LoginScreen(
                onNavigateToRegister = { navController.navigate(Screen.Register.route) },
                onLoginSuccess = {
                    navController.navigate(Screen.Dashboard.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                }
            )
        }
        composable(Screen.Dashboard.route) {
            DashboardScreen(
                onNavigateToSavings = { navController.navigate(Screen.SavingsHub.route) },
                onNavigateToRewards = { navController.navigate(Screen.Rewards.route) },
                onNavigateToProfile = { navController.navigate(Screen.Profile.route) }
            )
        }
        composable(Screen.SavingsHub.route) {
            SavingsHubScreen(
                onNavigateToDashboard = { navController.navigate(Screen.Dashboard.route) },
                onNavigateToRewards = { navController.navigate(Screen.Rewards.route) },
                onNavigateToProfile = { navController.navigate(Screen.Profile.route) }
            )
        }
        composable(Screen.Rewards.route) {
            RewardsScreen(
                onNavigateToDashboard = { navController.navigate(Screen.Dashboard.route) },
                onNavigateToSavings = { navController.navigate(Screen.SavingsHub.route) },
                onNavigateToProfile = { navController.navigate(Screen.Profile.route) }
            )
        }
        composable(Screen.Profile.route) {
            ProfileScreen(
                onNavigateToDashboard = { navController.navigate(Screen.Dashboard.route) },
                onNavigateToSavings = { navController.navigate(Screen.SavingsHub.route) },
                onNavigateToRewards = { navController.navigate(Screen.Rewards.route) },
                onLogout = {
                    navController.navigate(Screen.Splash.route) {
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }
    }
}
