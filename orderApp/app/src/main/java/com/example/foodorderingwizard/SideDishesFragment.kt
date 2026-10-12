package com.example.foodorderingwizard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.foodorderingwizard.databinding.FragmentSideDishesBinding

class SideDishesFragment : Fragment() {
    private var _binding: FragmentSideDishesBinding? = null
    private val binding get() = _binding!!
    private val vm: OrderViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSideDishesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val currentSides = vm.order.value?.sideDishes ?: mutableListOf()
        if (currentSides.contains("Fries")) binding.cbFries.isChecked = true
        if (currentSides.contains("Salad")) binding.cbSalad.isChecked = true
        if (currentSides.contains("Corn Cup")) binding.cbCornCup.isChecked = true

        binding.btnSideDone.setOnClickListener {
            val dishes = mutableListOf<String>()
            if (binding.cbFries.isChecked) dishes.add("Fries")
            if (binding.cbSalad.isChecked) dishes.add("Salad")
            if (binding.cbCornCup.isChecked) dishes.add("Corn Cup")

            vm.setSideDishes(dishes)
            parentFragmentManager.popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
