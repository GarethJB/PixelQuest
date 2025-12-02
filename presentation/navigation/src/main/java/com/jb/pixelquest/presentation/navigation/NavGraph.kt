package com.jb.pixelquest.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.jb.pixelquest.feature.studio.StudioRoute
import com.jb.pixelquest.feature.quest.QuestRoute
import com.jb.pixelquest.feature.gallery.GalleryRoute
import com.jb.pixelquest.feature.mypage.MyPageRoute

/**
 * 앱의 네비게이션 그래프를 설정하는 Composable
 */
@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = Route.Studio.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(
            route = Route.Studio.route,
            deepLinks = listOf(
                navDeepLink {
                    uriPattern = Route.getDeepLinkPattern(Route.Studio.route)
                }
            )
        ) {
            StudioRoute()
        }
        
        composable(
            route = Route.Quest.route,
            deepLinks = listOf(
                navDeepLink {
                    uriPattern = Route.getDeepLinkPattern(Route.Quest.route)
                }
            )
        ) {
            QuestRoute()
        }
        
        composable(
            route = Route.Gallery.route,
            deepLinks = listOf(
                navDeepLink {
                    uriPattern = Route.getDeepLinkPattern(Route.Gallery.route)
                }
            )
        ) {
            GalleryRoute()
        }
        
        composable(
            route = Route.MyPage.route,
            deepLinks = listOf(
                navDeepLink {
                    uriPattern = Route.getDeepLinkPattern(Route.MyPage.route)
                }
            )
        ) {
            MyPageRoute()
        }
    }
}

