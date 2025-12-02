package com.jb.pixelquest.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.jb.pixelquest.feature.home.HomeRoute

/**
 * 앱의 네비게이션 그래프를 설정하는 Composable
 */
@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = Route.Home.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(
            route = Route.Home.route,
            deepLinks = listOf(
                navDeepLink {
                    uriPattern = Route.getDeepLinkPattern(Route.Home.route)
                }
            )
        ) {
            HomeRoute()
        }
        
        composable(
            route = Route.Album.route,
            deepLinks = listOf(
                navDeepLink {
                    uriPattern = Route.getDeepLinkPattern(Route.Album.route)
                }
            )
        ) {
//            AlbumScreen()
        }
        
        composable(
            route = Route.Ranking.route,
            deepLinks = listOf(
                navDeepLink {
                    uriPattern = Route.getDeepLinkPattern(Route.Ranking.route)
                }
            )
        ) {
//            RankingScreen()
        }
        
        composable(
            route = Route.Settings.route,
            deepLinks = listOf(
                navDeepLink {
                    uriPattern = Route.getDeepLinkPattern(Route.Settings.route)
                }
            )
        ) {
//            SettingsScreen()
        }
    }
}

