package com.vit.test

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController

@Composable
fun MainActions(navController: NavHostController, onShuffle:() -> Unit) {
    IconButton(onClick = {
        navController.navigate(Route.AddDrink.name)
    }) {
        Icon(imageVector = Icons.Filled.Add, contentDescription = null)
    }

    IconButton(onClick = onShuffle) {
        Icon(imageVector = Icons.Filled.Refresh, contentDescription = null)
    }
}

@Composable
fun AddActions(navController: NavHostController) {
    IconButton(onClick = {
        navController.navigate(Route.Main.name)
    }) {
        Icon(imageVector = Icons.Default.Save, contentDescription = null)
    }
}