package com.jb.pixelquest.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jb.pixelquest.feature.gallery.GalleryRoute
import com.jb.pixelquest.feature.home.HomeRoute
import com.jb.pixelquest.feature.mypage.MyPageRoute
import com.jb.pixelquest.feature.quest.QuestProgressRoute
import com.jb.pixelquest.feature.quest.QuestRoute
import com.jb.pixelquest.feature.studio.StudioRoute

/**
 * 네비게이션 그래프
 * 모든 화면 Route를 연결하는 네비게이션 설정
 */
@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = PixelQuestDestination.Home.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(route = PixelQuestDestination.Home.route) {
            HomeRoute(navController = navController)
        }
        
        composable(route = PixelQuestDestination.Gallery.route) {
            GalleryRoute(navController = navController)
        }
        
        composable(route = PixelQuestDestination.Studio.route) {
            StudioRoute(navController = navController)
        }
        
        composable(route = PixelQuestDestination.Quest.route) {
            QuestRoute(navController = navController)
        }
        
        composable(route = PixelQuestDestination.QuestProgress.route) {
            QuestProgressRoute(navController = navController)
        }
        
        composable(route = PixelQuestDestination.MyPage.route) {
            MyPageRoute(navController = navController)
        }
    }
}

