package com.example.shoplistfragkotl

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment

class AddItemFragment : Fragment() {

    private lateinit var newItemEditText: EditText
    private lateinit var addButton: Button
    private var callback: OnNewItemAddedListener? = null

    interface OnNewItemAddedListener {
        fun onNewItemAdded(item: String)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = context as? OnNewItemAddedListener ?: throw  ClassCastException("$context должен реализовать Onhniu")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_add_item, container, false)

        newItemEditText = view. findViewById(R.id.ETProduct)
        addButton = view. findViewById(R.id.btnAdd)

        addButton.setOnClickListener {
            val newItem = newItemEditText.text.toString()
            if (newItem.isNotEmpty()) {
                Log.d("AddItemFragment", "Добавляем покупку: $newItem")
                callback?.onNewItemAdded(newItem)
                newItemEditText.text.clear()
            } else {
                Log.d("AddItemFragment", "Пустой ввод, покупка не добавлена")
                Toast.makeText(activity, "Введите элемент", Toast.LENGTH_SHORT).show()
            }
        }
            return view
        }
    }
