package com.vit.test

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun MainActions(navController: NavHostController, drinkList: DrinksViewModel) {
    IconButton(onClick = {
        navController.navigate(Route.AddDrink.name)
    }) {
        Icon(imageVector = Icons.Filled.Add, contentDescription = null)
    }

    IconButton(onClick = {
        drinkList.shuffle()
    }) {
        Icon(imageVector = Icons.Filled.Refresh, contentDescription = null)
    }
}

@Composable
fun AddActions(navController: NavHostController, onAdd: () -> Unit) {
    IconButton(onClick = onAdd) {
        Icon(imageVector = Icons.Default.Save, contentDescription = null)
    }
}