package com.example.shoplistfragkotl

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ShoppingListFragment: Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ShoppingListAdapter
    private val shoppingList = arrayListOf("Монако", "Хлэээб", "Абакан")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_shopping_list, container, false)

        recyclerView = view.findViewById(R.id.RVProduct)
        recyclerView.layoutManager = LinearLayoutManager(activity)

        adapter = ShoppingListAdapter(shoppingList)
        recyclerView.adapter = adapter

        return view
    }

    fun addItem(item: String) {
        Log.d("ShoppingListFragment", "Добавление новой покупки: $item")
        shoppingList.add(item)
        adapter.notifyDataSetChanged()
    }
}