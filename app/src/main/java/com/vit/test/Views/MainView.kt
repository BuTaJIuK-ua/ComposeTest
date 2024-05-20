package com.vit.test.Views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vit.test.Drink
import kotlin.random.Random

@Preview(device = Devices.PIXEL_3, showBackground = true)
@Composable
fun MainView(drinks: MutableList<Drink>, onRemove:(drink: Drink) -> Unit) {

    LazyColumn {
        item {
            Text(
                text = "Drinks: ",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineMedium
            )
        }
        items(drinks) {
            Card(
                modifier = Modifier.padding(4.dp),
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                )
                {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        Text(
                            text = it.name,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                         )
                        Row() {
                            for (item in it.ingredients) {
                                Text(
                                    text = item,
                                    modifier = Modifier.padding(end = 5.dp),
                                )
                            }
                        }
                    }
                    Button(onClick = { onRemove(it) }) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Remove",
                        )
                    }
                }
            }
        }
    }
}

fun defaultDrinks(): List<Drink> {
    val defaultNames = listOf("tea", "coffee", "water", "soda", "custom")
    val defaultIngredients = mutableListOf ("water", "sugar", "milk", "cream", "ice")

    val drinks: MutableList<Drink> = mutableListOf()

    for (i in 0..<10) {
        val nameNum =  Random.nextInt(defaultNames.size)
        var ingredientNum = Random.nextInt(defaultIngredients.size) + 1

        val ingredients = defaultIngredients.subList(0, ingredientNum)
        drinks.add(Drink(defaultNames[nameNum], ingredients))
    }

    return drinks
}