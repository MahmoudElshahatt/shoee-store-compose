package com.shahtott.shoee_store_compose.navigation

import kotlinx.serialization.Serializable
// Place this in a file like Screens.kt
sealed interface Destination {
    @Serializable data object Splash : Destination
    @Serializable data object Login : Destination
    @Serializable data object Register : Destination
    @Serializable data object Home : Destination
    @Serializable data class Profile(val userId: String) : Destination
    @Serializable data class Details(val productId: Int, val fromDeepLink: Boolean = false) :
        Destination
}