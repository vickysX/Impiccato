package com.example.impiccato.ui

interface NavigationDestination {
    val route: String
    val idArgs: String
    val routeWithArgs: String
}