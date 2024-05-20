package com.vit.test

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vit.test.Views.AddDrinkView
import com.vit.test.Views.MainView
import com.vit.test.Views.defaultDrinks

@Preview(device = Devices.PIXEL_3)
@Composable
fun RootScreen2() {
    val navController = rememberNavController()

    val currRoute = remember{ mutableStateOf(Route.Main.name) }
    navController.addOnDestinationChangedListener { _, dest, _ ->
        currRoute.value = dest.route ?: ""
    }

    var drinks by remember { mutableStateOf(defaultDrinks()) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    val title = if (currRoute.value == Route.Main.name){
                        "Choose Drink App"
                    } else {
                        currRoute.value
                    }

                    Text(title)
                },
                actions = {
                    when(currRoute.value)
                    {
                        Route.Main.name -> {
                            MainActions(navController, onShuffle = {
                                drinks = drinks.toMutableList().apply {
                                    shuffle()
                                }
                            })
                        }
                        Route.AddDrink.name -> { AddActions(navController) }
                        else -> {}
                    }
                },
                navigationIcon = if (currRoute.value != Route.Main.name) {
                    {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    }
                } else {
                    null
                }
            )
        },

    ) {
        NavHost(
            navController = navController,
            startDestination = Route.Main.name,
            modifier=Modifier.padding(it)
        ){
            composable(Route.Main.name) {
                MainView(drinks.toMutableList(),
                    onRemove = {
                    drinks = drinks.toMutableList().apply {
                        remove(it)
                    }
                })
            }
            composable(Route.AddDrink.name) { AddDrinkView() }
        }
    }
}

@Preview
@Composable
fun RootScreen() {
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



}


