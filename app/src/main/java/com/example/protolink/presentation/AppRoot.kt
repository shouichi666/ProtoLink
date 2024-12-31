package com.example.protolink.presentation

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun Root() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            val currentBackStack by navController.currentBackStackEntryAsState()
            val currentRoute = currentBackStack?.destination?.route

            if (currentRoute != Route.HomeGroup.Form.route) {
                RootBottomBar(navController = navController)
            }
        },
    ) { insetPadding ->
        AppNavHost(
            modifier = Modifier.padding(bottom = insetPadding.calculateBottomPadding()),
            navController = navController,
        )
    }
}

@Composable
fun RootBottomBar(navController: NavController) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = backStackEntry?.destination

    NavigationBar {
        Route.barItems().forEach { item ->
            val selected = currentDestination?.hierarchy?.any { it.route == item.route } == true

            NavigationBarItem(
                selected = selected,
                onClick = {
                    navController.navigate(item.route) {
                        Log.i("onTap", item.route)
                        // 最初の画面までバックスタックを popUp する
                        // (タブによる遷移によってバックスタックが際限なく増えることを抑止する
                        popUpTo(navController.graph.findStartDestination().id) {
                            // バックスタックから取り除いた画面の状態を保存しておく？
                            saveState =
                                when (item.route) {
                                    Route.HomeGroup.route -> false
                                    else -> true
                                }
                        }
                        // バックスタックのトップが同じ destination の場合に遷移しない
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                label = { Text(text = item.label) },
                icon = { Icon(item.icon, contentDescription = null) },
            )
        }
    }
}
