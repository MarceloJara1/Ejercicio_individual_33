package com.example.ejercicioindividual33.View

import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.ejercicioindividual33.Model.Item
import com.example.ejercicioindividual33.databinding.ItemRecyclerBinding

class ItemViewHolder (private val binding: ItemRecyclerBinding): RecyclerView.ViewHolder(binding.root){

    fun bind(item: Item){
        binding.nombreItem.text = item.itemName
    }
    val btnDelete: Button = binding.btnBorrar
}