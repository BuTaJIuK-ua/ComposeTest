package com.vit.test.Views

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.vit.test.DrinksViewModel

@OptIn(ExperimentalFoundationApi::class)
@Preview(device = Devices.PIXEL_3, showBackground = true)
@Composable
fun MainView(navController: NavHostController, drinksVM: DrinksViewModel) {
    val drinks by drinksVM.drinks.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { androidx.compose.material.Text("Choose Drink App") },
                actions = { MainActions(navController, drinksVM) },
            )
        }
    ) {

        LazyColumn(modifier = Modifier.padding(it) ) {
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

            items(drinks, key = {it.id}) {
                Card(modifier = Modifier
                    .padding(4.dp)
                    .animateItemPlacement(
                        animationSpec = tween(durationMillis = 600, easing = FastOutSlowInEasing)
                    )
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
                        Button(onClick = { drinksVM.removeDrink(it) }) {
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
}