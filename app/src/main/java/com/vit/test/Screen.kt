package com.vit.test

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vit.test.Views.AddDrinkView
import com.vit.test.Views.MainView

@Preview(device = Devices.PIXEL_3)
@Composable
fun RootScreen() {
    val navController = rememberNavController()
    val drinksVM = remember { DrinksViewModel() }

    NavHost(
        navController = navController,
        startDestination = Route.Main.name) {
        composable(Route.Main.name) { MainView(navController, drinksVM) }
        composable(Route.AddDrink.name) { AddDrinkView(navController, drinksVM) }
    }
}

    // screen1:
    // toolbar
    //     add drink button (navigate to screen2)
    //     shuffle button (reorders drinks))
    // list of drinks
    //   item1 () ()
    //       drink name
    //       list of ingredients
    //       X delete
    //   item2 (X delete)
    //   ...(default - 10 items)


    // screen2:
    // toolbar (back)
    //     add button
    // textField - drink name
    // ingredient1 - switch
    // ingredient2 - switch
    // ingredient3 - switch
    // ....

    // ingredients: water, sugar, milk, coffee, ice


