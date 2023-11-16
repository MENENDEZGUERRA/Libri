package com.example.libriv2.ui.Navigation

sealed class TabScreens(val route: String){
    object MainScreen: TabScreens("main")
    object BookPageScreen: TabScreens("bookpage")
    object ChatScreen: TabScreens("chat")
    object ProfileScreen: TabScreens("profile")
    object AdventureScreen: TabScreens("adventure")
    object HorrorScreen: TabScreens("horror")
    object ActionScreen: TabScreens("action")
    object RomanceScreen: TabScreens("romance")
    object LibriLoginScreen: TabScreens("login")
}

