package com.vit.test

import androidx.compose.runtime.State
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

data class Drink(
    var name: String,
    val ingredients: MutableList<String> = mutableListOf()
) {}

class DrinkList: ViewModel() {
    private val _drinks = MutableLiveData<List<Drink>>()
}