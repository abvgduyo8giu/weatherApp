package com.example.shopinglistkotling

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.activity.enableEdgeToEdge

class MainActivity : AppCompatActivity() {

    private lateinit var ListView: ListView
    private lateinit var addItemButton: Button
    private val shoppingList = mutableListOf<String>()
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ListView = findViewById(R.id.shopping_list_view)
        addItemButton = findViewById(R.id.add_item_button)

        adapter = ShoppingListAdapter(this, shoppingList)
        ListView.adapter = adapter

        addItemButton.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivityForResult(intent, 1)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d("MainActivity", "onActivityResult: requestCode = $requestCode, resultCode = $resultCode")
        if (requestCode == 1 && resultCode == RESULT_OK) {
            val newItem = data?.getStringExtra("newItem");
            Log.d("MainActivity", "Полученная покупка: $newItem")
            if (newItem != null) {
                shoppingList.add(newItem);
                adapter.notifyDataSetChanged();
                Log.d("MainActivity", "Покупка добавлена: $newItem")
            } else {
                Log.d("MainActivity", "Ошибка: получено null значение")
            }
        }
        else
        {
            Log.d("MainActivity", "Ошибка: результат не OK или неверный requestCode")
        }
    }
}
