package com.example.libriv2.ui.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.libriv2.ui.Screens.Action.actionB
import com.example.libriv2.ui.Screens.Adventure.adventure
import com.example.libriv2.ui.Screens.BookPage.pageContent
import com.example.libriv2.ui.Screens.Chat.ChatContent
import com.example.libriv2.ui.Screens.Horror.horror
import com.example.libriv2.ui.Screens.Login.LibriLoginScreen
import com.example.libriv2.ui.Screens.Login.LoginScreenViewModel
import com.example.libriv2.ui.Screens.Main.principal
import com.example.libriv2.ui.Screens.ProfileScreen.profile
import com.example.libriv2.ui.Screens.Romance.romance


@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    NavHost(navController= navController, startDestination= TabScreens.LibriLoginScreen.route){
        composable(route = TabScreens.MainScreen.route){
            principal(navController)
        }
        composable(route = TabScreens.BookPageScreen.route){
            pageContent(navController)
        }
        composable(route = TabScreens.ChatScreen.route){
            ChatContent(navController)
        }
        composable(route = TabScreens.ProfileScreen.route){
            profile(navController)
        }
        composable(route = TabScreens.AdventureScreen.route){
            adventure(navController)
        }
        composable(route = TabScreens.ActionScreen.route){
            actionB(navController)
        }
        composable(route = TabScreens.HorrorScreen.route){
            horror(navController)
        }
        composable(route = TabScreens.RomanceScreen.route){
            romance(navController)
        }
        composable(route = TabScreens.LibriLoginScreen.route){
            LibriLoginScreen(LoginScreenViewModel(), navController)
        }
    }
}