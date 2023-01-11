package com.danielpriddle.drpnews.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.danielpriddle.drpnews.ui.screens.*
import com.danielpriddle.drpnews.ui.theme.DRPNewsTheme
import com.danielpriddle.drpnews.utils.Route
import com.danielpriddle.drpnews.utils.navigate
import com.danielpriddle.drpnews.viewmodels.*
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

const val ARTICLE_KEY = "article"

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var articleListViewModel: ArticleListViewModel

    @Inject
    lateinit var articleSearchViewModel: ArticleSearchViewModel

    @Inject
    lateinit var settingsViewModel: SettingsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val isDarkMode = settingsViewModel.isDarkMode.collectAsState().value
            DRPNewsTheme(isDarkMode) {
                val navController = rememberNavController()
                Scaffold(
                    topBar = { TopBar() },
                    bottomBar = { BottomNavigationBar(navController = navController) },
                    content = { padding ->
                        Box(modifier = Modifier.padding(padding)) {
                            NavHost(
                                navController = navController,
                                startDestination = Route.Home.route
                            ) {
                                composable(Route.Home.route) {
                                    ArticleListScreen(
                                        articleListViewModel = articleListViewModel,
                                        clickListener = { article ->
                                            navigateToDetails(navController,
                                                article)
                                        }
                                    )
                                }
                                composable(Route.Search.route) {
                                    ArticleSearchScreen(
                                        articleSearchViewModel = articleSearchViewModel,
                                        clickListener = { article ->
                                            navigateToDetails(navController,
                                                article)
                                        }
                                    )
                                }
                                composable(
                                    route = Route.Details.route,
                                ) { backStackEntry ->
                                    val article =
                                        backStackEntry.arguments?.getParcelable<Article>(ARTICLE_KEY)
                                    requireNotNull(article) { stringResource(id = R.string.detail_route_error) }
                                    ArticleDetailsScreen(article)
                                }
                                composable(Route.Settings.route) {
                                    SettingsScreen(settingsViewModel)
                                }

                            }
                        }
                    },
                )
            }
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
                icon = { Icon(imageVector = route.icon, contentDescription = route.title) },
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





