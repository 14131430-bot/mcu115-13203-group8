package com.example.foodorderingwizard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.foodorderingwizard.databinding.FragmentMainMealBinding

class MainMealFragment : Fragment() {
    private var _binding: FragmentMainMealBinding? = null
    private val binding get() = _binding!!
    private val vm: OrderViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainMealBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vm.order.value?.mainMeal?.let { meal ->
            when (meal) {
                "Burger" -> binding.rbBurger.isChecked = true
                "Fried Chicken" -> binding.rbFriedChicken.isChecked = true
                "Chicken Nuggets" -> binding.rbChickenNuggets.isChecked = true
            }
        }

        binding.btnMainDone.setOnClickListener {
            val selectedId = binding.rgMainMeal.checkedRadioButtonId
            val meal = when (selectedId) {
                binding.rbBurger.id -> "Burger"
                binding.rbFriedChicken.id -> "Fried Chicken"
                binding.rbChickenNuggets.id -> "Chicken Nuggets"
                else -> null
            }
            if (meal != null) {
                vm.setMainMeal(meal)
            }
            parentFragmentManager.popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
