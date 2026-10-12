package com.example.orderapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.widget.ViewPager2

class MainMealFragment : Fragment(R.layout.fragment_main_meal) {
    private val viewModel: OrderViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnDone = view.findViewById<Button>(R.id.btnDoneMain)
        val rbBurger = view.findViewById<RadioButton>(R.id.rbBurger)
        val rbFriedChicken = view.findViewById<RadioButton>(R.id.rbFriedChicken)
        val rbChickenNuggets = view.findViewById<RadioButton>(R.id.rbChickenNuggets)

        val currentMain = viewModel.order.value?.mainMeal
        if (currentMain == "Burger") rbBurger.isChecked = true
        if (currentMain == "Fried Chicken") rbFriedChicken.isChecked = true
        if (currentMain == "Chicken Nuggets") rbChickenNuggets.isChecked = true

        btnDone.setOnClickListener {
            val selectedText = when {
                rbBurger.isChecked -> "Burger"
                rbFriedChicken.isChecked -> "Fried Chicken"
                rbChickenNuggets.isChecked -> "Chicken Nuggets"
                else -> null
            }
            if (selectedText != null) {
                viewModel.setMainMeal(selectedText)
            }
            val viewPager = requireActivity().findViewById<ViewPager2>(R.id.viewPager)
            viewPager?.currentItem = 1
        }
    }
}
