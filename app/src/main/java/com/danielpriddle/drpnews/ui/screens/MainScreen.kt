package com.danielpriddle.drpnews.ui.screens

import android.os.Bundle
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.danielpriddle.drpnews.R
import com.danielpriddle.drpnews.data.models.Article
import com.danielpriddle.drpnews.ui.theme.DRPNewsTheme
import com.danielpriddle.drpnews.utils.Route
import com.danielpriddle.drpnews.utils.navigate

const val ARTICLE_KEY = "article"

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        topBar = { TopBar() },
        bottomBar = { BottomNavigationBar(navController = navController) },
        content = { padding ->
            Box(modifier = Modifier.padding(padding)) {
                AppNavHost(navController)
            }
        },
    )
}

@Preview
@Composable
fun MainScreenPreview() {
    DRPNewsTheme {
        MainScreen()
    }
}

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Route.Home.route
    ) {
        composable(Route.Home.route) {
            ArticleListScreen(
                clickListener = { article -> navigateToDetails(navController, article) }
            )
        }
        composable(Route.Search.route) {
            ArticleSearchScreen(
                clickListener = { article -> navigateToDetails(navController, article) }
            )
        }
        composable(
            route = Route.Details.route,
        ) { backStackEntry ->
            val article = backStackEntry.arguments?.getParcelable<Article>("article")
            requireNotNull(article) { "Please make sure you are passing an article title" }
            ArticleDetailsScreen(article)
        }
        composable(Route.Settings.route) {
            SettingsScreen()
        }

    }
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    val routes = listOf(
        Route.Home,
        Route.Search,
        Route.Settings
    )
    BottomNavigation(
        backgroundColor = MaterialTheme.colors.primary,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        routes.forEach { route ->
            BottomNavigationItem(
                icon = { Icon(painterResource(id = route.icon), contentDescription = route.title) },
                label = { Text(text = route.title) },
                selectedContentColor = MaterialTheme.colors.secondary,
                unselectedContentColor = MaterialTheme.colors.secondary.copy(0.4f),
                alwaysShowLabel = true,
                selected = currentRoute == route.route,
                onClick = {
                    navController.navigate(route.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Preview
@Composable
fun BottomNavigationBarPreview() {
    DRPNewsTheme {
        BottomNavigationBar(navController = rememberNavController())
    }
}

@Composable
fun TopBar() {
    TopAppBar(
        backgroundColor = MaterialTheme.colors.primary,
        title = {
            Box(
                Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(R.string.app_name),
                    style = MaterialTheme.typography.h4,
                )
            }
        })
}

@Preview
@Composable
fun TopBarPreview() {
    DRPNewsTheme {
        TopBar()
    }
}

private fun navigateToDetails(navController: NavHostController, article: Article) {
    val bundle = Bundle()
    bundle.putParcelable(ARTICLE_KEY, article)
    navController.navigate(Route.Details.route, bundle)
}