package com.vit.test

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.UUID
import kotlin.random.Random

data class Drink(
    var name: String,
    val ingredients: List<String> = listOf(),
    val id: UUID = UUID.randomUUID(),
) {}

class DrinksViewModel: ViewModel() {
    private val _drinks = MutableStateFlow(defaultDrinks())
    val drinks: StateFlow<List<Drink>> = _drinks

    fun addDrink(drink: Drink) {
        _drinks.value = _drinks.value.toMutableList().apply { add(drink) }
    }

    fun shuffle() {
        _drinks.value = _drinks.value.toMutableList().apply { shuffle() }
    }

    fun removeDrink(drink: Drink) {
        _drinks.value = _drinks.value.toMutableList().apply { remove(drink) }
    }

    companion object {
        private val defaultNames = listOf("tea", "coffee", "water", "soda", "custom")
        val defaultIngredients = listOf("water", "sugar", "milk", "cream", "ice")
        fun defaultDrinks(): List<Drink> {
            val drinks: MutableList<Drink> = mutableListOf()

            for (i in 0..<10) {
                val nameNum = Random.nextInt(defaultNames.size)
                val ingredientNum = Random.nextInt(defaultIngredients.size) + 1

                val ingredients = defaultIngredients.subList(0, ingredientNum)
                drinks.add(Drink(defaultNames[nameNum], ingredients))
            }

            return drinks
        }
    }
}