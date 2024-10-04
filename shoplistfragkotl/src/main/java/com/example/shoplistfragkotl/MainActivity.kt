package com.example.shoplistfragkotl

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity(), AddItemFragment. OnNewItemAddedListener {

    private lateinit var shoppingListFragment: ShoppingListFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            shoppingListFragment = ShoppingListFragment()

            val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            transaction.add(R.id.shopping_list_container, shoppingListFragment)

            val addItemFragment = AddItemFragment()
            transaction.add(R.id.add_item_container, addItemFragment)

            transaction.commit()
        }
    }

    override fun onNewItemAdded(item: String) {
        shoppingListFragment.addItem(item)
    }
}