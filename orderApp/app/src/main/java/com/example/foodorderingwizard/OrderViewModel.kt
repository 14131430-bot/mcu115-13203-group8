package com.example.foodorderingwizard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OrderViewModel : ViewModel() {
    private val _order = MutableLiveData(Order())
    val order: LiveData<Order> get() = _order

    fun setMainMeal(meal: String) {
        val current = _order.value ?: Order()
        current.mainMeal = meal
        _order.value = current
    }

    fun setSideDishes(dishes: MutableList<String>) {
        val current = _order.value ?: Order()
        current.sideDishes = dishes
        _order.value = current
    }

    fun setDrink(drinkName: String) {
        val current = _order.value ?: Order()
        current.drink = drinkName
        _order.value = current
    }

    fun resetOrder() {
        _order.value = Order()
    }
}
