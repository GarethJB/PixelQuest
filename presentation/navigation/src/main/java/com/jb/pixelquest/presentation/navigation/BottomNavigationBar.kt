package com.jb.pixelquest.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.jb.pixelquest.presentation.navigation.Route.Studio
import com.jb.pixelquest.presentation.navigation.Route.Quest
import com.jb.pixelquest.presentation.navigation.Route.Gallery
import com.jb.pixelquest.presentation.navigation.Route.MyPage
import com.jb.pixelquest.presentation.resources.R

/**
 * Bottom Navigation Bar의 아이템 정보
 */
data class BottomNavItem(
    val route: Route,
    val labelResId: Int,
    val icon: androidx.compose.ui.graphics.vector.ImageVector
)

/**
 * Bottom Navigation Bar Composable
 */
@Composable
fun BottomNavigationBar(
    navController: androidx.navigation.NavHostController
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    
    val bottomNavItems = listOf(
        BottomNavItem(Studio, R.string.nav_studio, Icons.Default.Create),
        BottomNavItem(Quest, R.string.nav_quest, Icons.Default.Check),
        BottomNavItem(Gallery, R.string.nav_gallery, Icons.Default.Favorite),
        BottomNavItem(MyPage, R.string.nav_mypage, Icons.Default.Face)
    )
    
    NavigationBar {
        bottomNavItems.forEach { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = stringResource(id = item.labelResId)
                    )
                },
                label = { Text(stringResource(id = item.labelResId)) },
                selected = currentDestination?.hierarchy?.any { it.route == item.route.route } == true,
                onClick = {
                    navController.navigate(item.route.route) {
                        // Bottom Navigation에서 뒤로가기 스택을 유지하지 않도록 설정
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

