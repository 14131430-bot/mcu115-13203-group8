package com.example.foodorderingwizard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.foodorderingwizard.databinding.FragmentDrinkBinding

class DrinkFragment : Fragment() {
    private var _binding: FragmentDrinkBinding? = null
    private val binding get() = _binding!!
    private val vm: OrderViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDrinkBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vm.order.value?.drink?.let { drink ->
            when (drink) {
                "Cola" -> binding.rbCola.isChecked = true
                "Iced Tea" -> binding.rbIcedTea.isChecked = true
                "Water" -> binding.rbWater.isChecked = true
            }
        }

        binding.btnDrinkDone.setOnClickListener {
            val selectedId = binding.rgDrink.checkedRadioButtonId
            val drink = when (selectedId) {
                binding.rbCola.id -> "Cola"
                binding.rbIcedTea.id -> "Iced Tea"
                binding.rbWater.id -> "Water"
                else -> null
            }
            if (drink != null) {
                vm.setDrink(drink)
            }
            parentFragmentManager.popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
