package com.example.lab_1_teor

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputNumber = findViewById<EditText>(R.id.inputNumber)
        val calculateButton = findViewById<Button>(R.id.calculateButton)
        val resultText = findViewById<TextView>(R.id.resultText)

        calculateButton.setOnClickListener {
            val userInput = inputNumber.text.toString().toIntOrNull()

            if (userInput == null || userInput !in 0..765) {
                resultText.text = "Введіть число від 0 до 765!"
                return@setOnClickListener
            }

            val probability = calculateProbability(userInput)
            resultText.text = "Ймовірність: %.6f".format(probability)
        }
    }

    private fun calculateProbability(threshold: Int): Double {
        var validCount = 0
        val totalCombinations = 2829056.0

        for (a in 0..255) {
            for (b in a..255) {
                for (c in b..255) {
                    if (a + b + c > threshold) {
                        validCount++
                    }
                }
            }
        }

        return validCount / totalCombinations
    }
}
