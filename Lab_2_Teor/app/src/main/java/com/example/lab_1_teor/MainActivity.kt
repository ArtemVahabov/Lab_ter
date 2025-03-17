package com.example.lab_1_teor

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.pow
import kotlin.math.sqrt
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private val m = 5.0 // математичне сподівання
    private val a = 5.0 // параметр a
    private val n = 5000 // кількість значень

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val resultText: TextView = findViewById(R.id.resultText)
        val generateButton: Button = findViewById(R.id.generateButton)

        generateButton.setOnClickListener {
            val randomNumbers = List(n) { Random.nextDouble(0.0, 1.0) }

            val simpsonValues = randomNumbers.map { r ->
                if (r < 0.5) {
                    sqrt(2 * r * a.pow(2)) + (m - a)
                } else {
                    (m + a) - sqrt((1 - r) * 2 * a.pow(2))
                }
            }

            val meanExp = simpsonValues.average()
            val sigmaExp = sqrt(simpsonValues.map { (it - meanExp).pow(2) }.sum() / (n - 1))

            val sigmaTheo = a / sqrt(6.0)
            val intervalMin = m - sigmaTheo
            val intervalMax = m + sigmaTheo
            val pTheo = simpsonValues.count { it in intervalMin..intervalMax }.toDouble() / n

            val intervalMinExp = meanExp - sigmaExp
            val intervalMaxExp = meanExp + sigmaExp
            val pExp = simpsonValues.count { it in intervalMinExp..intervalMaxExp }.toDouble() / n

            resultText.text = """
                Теоретичне M[X]: $m
                Експериментальне M[X]: ${"%.4f".format(meanExp)}
                Теоретичне σ: ${"%.4f".format(sigmaTheo)}
                Експериментальне σ: ${"%.4f".format(sigmaExp)}
                Теоретичний інтервал: [${"%.4f".format(intervalMin)}, ${"%.4f".format(intervalMax)}]
                Експериментальний інтервал: [${"%.4f".format(intervalMinExp)}, ${"%.4f".format(intervalMaxExp)}]
                Теоретична ймовірність: ${"%.4f".format(pTheo)}
                Експериментальна ймовірність: ${"%.4f".format(pExp)}
            """.trimIndent()
        }
    }
}

