package com.example.ui.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.data.viewmodel.HistoryViewModel

enum class MainTab(
    val title: String,
    val icon: ImageVector,
    val testTag: String
) {
    HOME("Khám Phá", Icons.Default.Explore, "tab_home"),
    TIMELINE("Thời Gian", Icons.Default.History, "tab_timeline"),
    QUIZ("Trắc Nghiệm", Icons.Default.Psychology, "tab_quiz"),
    AI("Hỏi AI", Icons.Default.AutoAwesome, "tab_ai"),
    BOOKMARKS("Đã Lưu", Icons.Default.Bookmark, "tab_bookmarks")
}

@Composable
fun MainAppScreen(
    viewModel: HistoryViewModel = viewModel()
) {
    val navController = rememberNavController()
    var activeTab by remember { mutableStateOf(MainTab.HOME) }
    var initialAiPrompt by remember { mutableStateOf<String?>(null) }

    NavHost(navController = navController, startDestination = "main_tabs") {
        composable("main_tabs") {
            Scaffold(
                bottomBar = {
                    NavigationBar(
                        tonalElevation = 8.dp
                    ) {
                        MainTab.values().forEach { tab ->
                            NavigationBarItem(
                                selected = activeTab == tab,
                                onClick = { activeTab = tab },
                                icon = {
                                    Icon(
                                        imageVector = tab.icon,
                                        contentDescription = tab.title
                                    )
                                },
                                label = { Text(tab.title) },
                                modifier = Modifier.testTag(tab.testTag)
                            )
                        }
                    }
                }
            ) { innerPadding ->
                Surface(modifier = Modifier.padding(innerPadding)) {
                    AnimatedContent(targetState = activeTab, label = "tab_transition") { tab ->
                        when (tab) {
                            MainTab.HOME -> HomeScreen(
                                viewModel = viewModel,
                                onEventClick = { eventId ->
                                    navController.navigate("detail/$eventId")
                                },
                                onAskAiClick = {
                                    activeTab = MainTab.AI
                                }
                            )
                            MainTab.TIMELINE -> TimelineScreen(
                                viewModel = viewModel,
                                onEventClick = { eventId ->
                                    navController.navigate("detail/$eventId")
                                }
                            )
                            MainTab.QUIZ -> QuizScreen(
                                viewModel = viewModel
                            )
                            MainTab.AI -> GeminiAiScreen(
                                viewModel = viewModel,
                                initialQuestion = initialAiPrompt
                            )
                            MainTab.BOOKMARKS -> BookmarksScreen(
                                viewModel = viewModel,
                                onEventClick = { eventId ->
                                    navController.navigate("detail/$eventId")
                                }
                            )
                        }
                    }
                }
            }
        }

        composable(
            route = "detail/{eventId}",
            arguments = listOf(navArgument("eventId") { type = NavType.StringType })
        ) { backStackEntry ->
            val eventId = backStackEntry.arguments?.getString("eventId") ?: ""
            DetailScreen(
                eventId = eventId,
                viewModel = viewModel,
                onBackClick = { navController.popBackStack() },
                onAskAiAboutTopic = { prompt ->
                    initialAiPrompt = prompt
                    activeTab = MainTab.AI
                    navController.popBackStack()
                }
            )
        }
    }
}
