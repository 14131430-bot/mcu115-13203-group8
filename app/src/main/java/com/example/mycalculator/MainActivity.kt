package com.example.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    // 宣告畫面上的元件變數
    private lateinit var tvInput: TextView
    private lateinit var tvOldInput: TextView

    private var firstNumber = 0.0
    private var currentOperator = ""
    private var isNewInput = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. 綁定顯示數值的 TextView
        tvInput = findViewById(R.id.tvInput)
        tvOldInput = findViewById(R.id.tvOldInput)

        // 2. 綁定數字按鈕
        setupNumberButtons()

        // 3. 綁定功能與運算按鈕
        setupOperatorButtons()
    }

    private fun setupNumberButtons() {
        // 建立按鈕 ID 與數字對應的清單
        val numberMap = mapOf(
            R.id.btnZero to "0",
            R.id.btnOne to "1",
            R.id.btnTwo to "2",
            R.id.btnThree to "3",
            R.id.btnFour to "4",
            R.id.btnFive to "5",
            R.id.btnSix to "6",
            R.id.btnSeven to "7",
            R.id.btnEight to "8",
            R.id.btnNine to "9",
            R.id.btnDot to "."
        )

        for ((id, digit) in numberMap) {
            findViewById<View>(id)?.setOnClickListener {
                val currentText = tvInput.text.toString()
                if (digit == "." && currentText.contains(".")) {
                    return@setOnClickListener
                }
                if (isNewInput) {
                    if (digit == ".") {
                        tvInput.text = "0."
                    } else {
                        tvInput.text = digit
                    }
                    isNewInput = false
                } else {
                    tvInput.text = "$currentText$digit"
                }
            }
        }
    }

    private fun setupOperatorButtons() {
        val operatorMap = mapOf(
            R.id.btnDivide to "÷",
            R.id.btnMultiply to "×",
            R.id.btnMinus to "-",
            R.id.btnPLus to "+"
        )

        for ((id, op) in operatorMap) {
            findViewById<View>(id)?.setOnClickListener {
                firstNumber = tvInput.text.toString().toDoubleOrNull() ?: 0.0
                currentOperator = op
                tvOldInput.text = "$firstNumber $currentOperator"
                isNewInput = true
            }
        }

        // 等於按鈕 (=)
        findViewById<View>(R.id.btnEqual)?.setOnClickListener {
            val secondNumber = tvInput.text.toString().toDoubleOrNull() ?: 0.0
            val result = when (currentOperator) {
                "+" -> firstNumber + secondNumber
                "-" -> firstNumber - secondNumber
                "×" -> firstNumber * secondNumber
                "÷" -> if (secondNumber != 0.0) firstNumber / secondNumber else Double.NaN
                else -> secondNumber
            }
            tvOldInput.text = "$firstNumber $currentOperator $secondNumber ="
            tvInput.text = if (result.isNaN()) "Error" else if (result == result.toInt().toDouble()) result.toInt().toString() else result.toString()
            firstNumber = if (result.isNaN()) 0.0 else result
            isNewInput = true
        }

        // 清除按鈕 (C)
        findViewById<View>(R.id.clear)?.setOnClickListener {
            tvInput.text = "0"
            isNewInput = true
        }

        // 全部清除按鈕 (AC)
        findViewById<View>(R.id.allClear)?.setOnClickListener {
            tvInput.text = "0"
            tvOldInput.text = ""
            firstNumber = 0.0
            currentOperator = ""
            isNewInput = true
        }

        // 倒退刪除鍵 (Backspace)
        findViewById<View>(R.id.btnBackspace)?.setOnClickListener {
            val currentText = tvInput.text.toString()
            if (currentText.length > 1 && currentText != "Error") {
                tvInput.text = currentText.substring(0, currentText.length - 1)
            } else {
                tvInput.text = "0"
                isNewInput = true
            }
        }
    }
}
