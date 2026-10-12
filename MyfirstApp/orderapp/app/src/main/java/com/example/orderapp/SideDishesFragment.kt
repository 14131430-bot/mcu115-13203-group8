package com.example.orderapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.widget.ViewPager2

class SideDishesFragment : Fragment(R.layout.fragment_side_dishes) {
    private val viewModel: OrderViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnDone = view.findViewById<Button>(R.id.btnDoneSides)
        val cbFries = view.findViewById<CheckBox>(R.id.cbFries)
        val cbSalad = view.findViewById<CheckBox>(R.id.cbSalad)
        val cbCornCup = view.findViewById<CheckBox>(R.id.cbCornCup)

        val currentSides = viewModel.order.value?.sideDishes ?: mutableListOf()
        if (currentSides.contains("Fries")) cbFries.isChecked = true
        if (currentSides.contains("Salad")) cbSalad.isChecked = true
        if (currentSides.contains("Corn Cup")) cbCornCup.isChecked = true

        btnDone.setOnClickListener {
            val selectedSides = mutableListOf<String>()
            if (cbFries.isChecked) selectedSides.add("Fries")
            if (cbSalad.isChecked) selectedSides.add("Salad")
            if (cbCornCup.isChecked) selectedSides.add("Corn Cup")

            viewModel.setSideDishes(selectedSides)
            val viewPager = requireActivity().findViewById<ViewPager2>(R.id.viewPager)
            viewPager?.currentItem = 2
        }
    }
}
