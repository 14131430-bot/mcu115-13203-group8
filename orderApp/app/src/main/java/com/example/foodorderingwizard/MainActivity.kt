package com.example.foodorderingwizard

import android.app.AlertDialog
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.foodorderingwizard.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val vm: OrderViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnChooseMainMeal.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(android.R.id.content, MainMealFragment())
                .addToBackStack(null)
                .commit()
        }

        binding.btnChooseSideDishes.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(android.R.id.content, SideDishesFragment())
                .addToBackStack(null)
                .commit()
        }

        binding.btnChooseDrink.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(android.R.id.content, DrinkFragment())
                .addToBackStack(null)
                .commit()
        }

        vm.order.observe(this) { order ->
            val mainStr = order.mainMeal ?: "-"
            val sidesStr = if (order.sideDishes.isEmpty()) "-" else order.sideDishes.joinToString(", ")
            val drinkStr = order.drink ?: "-"
            binding.tvCurrentSelection.text = "Current Selection:\nMain: $mainStr\nSides: $sidesStr\nDrink: $drinkStr"
        }

        binding.btnOrder.setOnClickListener {
            val order = vm.order.value
            if (order?.isComplete() != true) {
                Toast.makeText(
                    this,
                    "Please select a main meal, at least one side dish, and a drink.",
                    Toast.LENGTH_LONG
                ).show()
                return@setOnClickListener
            }

            AlertDialog.Builder(this)
                .setTitle("Submit Order")
                .setMessage(
                    "Main: ${order.mainMeal}\n" +
                    "Sides: ${order.sideDishes.joinToString(", ")}\n" +
                    "Drink: ${order.drink}\n\nSubmit this order?"
                )
                .setPositiveButton("Submit") { d, _ ->
                    d.dismiss()
                    Toast.makeText(this, "Order submitted! 🎉", Toast.LENGTH_SHORT).show()
                    finish()
                }
                .setNegativeButton("Cancel", null)
                .show()
        }
    }
}
