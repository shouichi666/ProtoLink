package com.example.protolink.presentation

import AboutScreen
import DetailScreen
import FormScreen
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.protolink.presentation.home.HomeScreen

sealed class Route(
    val route: String,
) {
    data object HomeGroup : Route("home_group") {
        data object Home : Route("home")

        data object Detail : Route("home/{detailId}") {
            val arguments = listOf(navArgument("detailId") { type = NavType.IntType })

            fun destination(id: Int) = "home/$id"

            fun getBookId(backStackEntry: NavBackStackEntry) = backStackEntry.arguments!!.getInt("detailId")
        }

        data object Form : Route("form")
    }

    data object About : Route("about")

    companion object {
        fun barItems() =
            listOf(
                BarItem("Home", Icons.Outlined.Home, HomeGroup.route),
                BarItem("About", Icons.Outlined.Settings, About.route),
            )
    }
}

class BarItem(
    val label: String,
    val icon: ImageVector,
    val route: String,
)

@Composable
fun AppNavHost(
    modifier: Modifier,
    navController: NavHostController,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Route.HomeGroup.route,
    ) {
        navigation(
            startDestination = Route.HomeGroup.Home.route,
            route = Route.HomeGroup.route,
        ) {
            composable(
                Route.HomeGroup.Home.route,
            ) {
                HomeScreen(
                    navController = navController,
                )
            }

            composable(
                Route.HomeGroup.Detail.route,
                arguments = Route.HomeGroup.Detail.arguments,
            ) { backStackEntry ->
                DetailScreen(
                    bookId = Route.HomeGroup.Detail.getBookId(backStackEntry),
                    navigateBack = { navController.popBackStack() },
                )
            }

            composable(
                Route.HomeGroup.Form.route,
            ) {
                FormScreen(
                    navController = navController,
                )
            }
        }

        composable(
            Route.About.route,
        ) {
            AboutScreen()
        }
    }
}
