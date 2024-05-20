package com.vit.test

sealed class Route(val name: String) {
    data object Main: Route("Main")
    data object AddDrink: Route("Add Drink")
}