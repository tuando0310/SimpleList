package com.example.simplelist

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var etNumber: EditText
    private lateinit var radioGroup: RadioGroup
    private lateinit var rbEven: RadioButton
    private lateinit var rbOdd: RadioButton
    private lateinit var rbSquare: RadioButton
    private lateinit var btnShow: Button
    private lateinit var listView: ListView
    private lateinit var tvError: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etNumber = findViewById(R.id.etNumber)
        radioGroup = findViewById(R.id.radioGroup)
        rbEven = findViewById(R.id.rbEven)
        rbOdd = findViewById(R.id.rbOdd)
        rbSquare = findViewById(R.id.rbSquare)
        btnShow = findViewById(R.id.btnShow)
        listView = findViewById(R.id.listView)
        tvError = findViewById(R.id.tvError)



        radioGroup.setOnCheckedChangeListener { _, _ ->
            btnShow.visibility = View.VISIBLE
        }

        btnShow.setOnClickListener {
            val input = etNumber.text.toString()

            if (input.isEmpty() || input.toInt() <= 0) {
                tvError.text = "Vui lòng nhập số nguyên dương!"
                tvError.visibility = View.VISIBLE
                listView.visibility = View.GONE // Ẩn danh sách nếu có lỗi
                return@setOnClickListener
            }

            val n = input.toInt()
            val result: List<Int>
            tvError.visibility = View.GONE // Ẩn thông báo lỗi nếu dữ liệu hợp lệ
            listView.visibility = View.VISIBLE // Hiển thị danh sách khi có dữ liệu hợp lệ

            when (radioGroup.checkedRadioButtonId) {
                R.id.rbEven -> result = getEvenNumbers(n)
                R.id.rbOdd -> result = getOddNumbers(n)
                R.id.rbSquare -> result = getSquareNumbers(n)
                else -> result = emptyList()
            }

            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, result)
            listView.adapter = adapter
        }
    }


    private fun getEvenNumbers(n: Int): List<Int> {
        val evenNumbers = mutableListOf<Int>()
        for (i in 0..n step 2) {
            evenNumbers.add(i)
        }
        return evenNumbers
    }

    private fun getOddNumbers(n: Int): List<Int> {
        val oddNumbers = mutableListOf<Int>()
        for (i in 1..n step 2) {
            oddNumbers.add(i)
        }
        return oddNumbers
    }

    private fun getSquareNumbers(n: Int): List<Int> {
        val squareNumbers = mutableListOf<Int>()
        var i = 0
        while (i * i <= n) {
            squareNumbers.add(i * i)
            i++
        }
        return squareNumbers
    }
}