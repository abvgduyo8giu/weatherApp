package com.example.shopinglistkotling

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class AddActivity : AppCompatActivity() {
    private lateinit var itemNameEditText: EditText
    private lateinit var saveButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.add_activity)

        itemNameEditText = findViewById(R.id.new_item_edit_text)
        saveButton = findViewById(R.id.save_item_button)

        saveButton.setOnClickListener {
            val newItem = itemNameEditText.text.toString()
            if (newItem.isNotEmpty()) {
                val resultIntent = Intent()
                resultIntent.putExtra("newItem", newItem)
                Log.d("AddItemActivity", "Покупка передана: $newItem")
                setResult(RESULT_OK, resultIntent)

            } else {
                setResult(RESULT_CANCELED)
                Log.d("AddItemActivity", "Покупка не добавлена, пустой ввод")
            }
            finish()
        }
    }
}