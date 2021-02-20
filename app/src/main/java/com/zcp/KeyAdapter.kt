package com.zcp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class KeyAdapter(private val context: Context) : RecyclerView.Adapter<KeyAdapter.KeyHolder>() {
    private val list: ArrayList<String> = ArrayList()
    var onItemClick: ((String) -> Unit)? = null

    init {
        list.add(Key.KEY_A)
        list.add(Key.KEY_2)
        list.add(Key.KEY_3)
        list.add(Key.KEY_4)
        list.add(Key.KEY_5)
        list.add(Key.KEY_6)
        list.add(Key.KEY_7)
        list.add(Key.KEY_8)
        list.add(Key.KEY_9)
        list.add(Key.KEY_10)
        list.add(Key.KEY_J)
        list.add(Key.KEY_Q)
        list.add(Key.KEY_K)
        list.add(Key.KEY_DEL)
        list.add(Key.KEY_CLEAR)
        list.add(Key.KEY_EQUAL)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KeyHolder {
        return KeyHolder(
            LayoutInflater.from(context).inflate(R.layout.item_jsq, parent, false)
        )
    }

    inner class KeyHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.text)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: KeyHolder, position: Int) {
        holder.textView.text = list[position]
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(list[position])
        }
    }

}