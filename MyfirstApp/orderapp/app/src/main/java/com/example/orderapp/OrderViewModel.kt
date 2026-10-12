package com.example.orderapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OrderViewModel : ViewModel() {
    private val _order = MutableLiveData(Order())
    val order: LiveData<Order> get() = _order

    fun setMainMeal(meal: String) {
        _order.value = _order.value?.copy(mainMeal = meal)
    }

    fun setSideDishes(dishes: MutableList<String>) {
        _order.value = _order.value?.copy(sideDishes = dishes)
    }

    fun setDrink(d: String) {
        _order.value = _order.value?.copy(drink = d)
    }
}
