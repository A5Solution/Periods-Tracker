package com.example.periodflow.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.periodflow.R

class NumbersAdapter(private val numbers: List<Int>) : RecyclerView.Adapter<NumberViewHolder>() {

    private var selectedPosition = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumberViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return NumberViewHolder(view)
    }

    override fun onBindViewHolder(holder: NumberViewHolder, position: Int) {
        holder.numberText.text = numbers[position].toString()
        holder.itemView.isSelected = position == selectedPosition
    }

    override fun getItemCount(): Int = numbers.size
}

class NumberViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val numberText: TextView = itemView.findViewById(R.id.numberTextView)
}