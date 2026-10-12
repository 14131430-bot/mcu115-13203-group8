package com.example.orderapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.widget.ViewPager2

class DrinkFragment : Fragment(R.layout.fragment_drink) {
    private val viewModel: OrderViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnDone = view.findViewById<Button>(R.id.btnDoneDrink)
        val rbCola = view.findViewById<RadioButton>(R.id.rbCola)
        val rbIcedTea = view.findViewById<RadioButton>(R.id.rbIcedTea)
        val rbWater = view.findViewById<RadioButton>(R.id.rbWater)

        val currentDrink = viewModel.order.value?.drink
        if (currentDrink == "Cola") rbCola.isChecked = true
        if (currentDrink == "Iced Tea") rbIcedTea.isChecked = true
        if (currentDrink == "Water") rbWater.isChecked = true

        btnDone.setOnClickListener {
            val selectedText = when {
                rbCola.isChecked -> "Cola"
                rbIcedTea.isChecked -> "Iced Tea"
                rbWater.isChecked -> "Water"
                else -> null
            }
            if (selectedText != null) {
                viewModel.setDrink(selectedText)
            }
            val viewPager = requireActivity().findViewById<ViewPager2>(R.id.viewPager)
            viewPager?.currentItem = 3
        }
    }
}
