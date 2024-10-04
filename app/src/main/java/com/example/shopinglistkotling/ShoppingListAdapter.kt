package com.example.shopinglistkotling

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ShoppingListAdapter (context: Context, shoppingList: MutableList<String>) :
    ArrayAdapter<String>(context, 0, shoppingList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var itemView = convertView
        val item = getItem(position)

        if (itemView == null) {
            itemView = LayoutInflater.from(context)
                .inflate(android.R.layout.simple_list_item_1, parent, false)
        }

        val textView = itemView!!.findViewById<TextView>(android.R.id.text1)
        textView.text = item

        return itemView
    }
}