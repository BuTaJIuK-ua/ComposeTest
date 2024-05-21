package com.vit.test.Views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.vit.test.Drink
import com.vit.test.DrinksViewModel
import com.vit.test.Route

@Preview(showBackground = true)
@Composable
fun AddDrinkView(navController: NavHostController, drinksVM: DrinksViewModel) {
    var drink by remember {
        mutableStateOf("Custom")
    }
    var ingredients by remember {
        mutableStateOf(listOf<String>())
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Add drink") },
                actions = { AddActions(navController) {
                    drinksVM.addDrink(Drink(drink, ingredients))
                    navController.navigate(Route.Main.name)
                } },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon( imageVector = Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) {
        Column(modifier = Modifier.padding(it)) {
            TextField(
                value = drink,
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Drink Name:") },
                onValueChange = { drink = it }
            )

            Spacer(modifier = Modifier.height(20.dp))

            LazyColumn(modifier = Modifier.padding(horizontal = 16.dp)) {
                items(DrinksViewModel.defaultIngredients) { item ->
                    Row(
                        modifier = Modifier.fillMaxWidth(0.75f),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            item,
                            modifier = Modifier.weight(1f)
                        )
                        Switch(
                            checked = ingredients.contains(item),
                            onCheckedChange = {
                                ingredients = ingredients.toMutableList().apply {
                                    if (it) {
                                        add(item)
                                    } else {
                                        remove(item)
                                    }
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}
