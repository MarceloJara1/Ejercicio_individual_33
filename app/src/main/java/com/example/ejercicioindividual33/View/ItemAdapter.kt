package com.example.ejercicioindividual33.View

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.ejercicioindividual33.Model.Item
import com.example.ejercicioindividual33.ViewModel.ItemViewModel
import com.example.ejercicioindividual33.databinding.ItemRecyclerBinding

class ItemAdapter(private val itemViewModel: ItemViewModel): RecyclerView.Adapter<ItemViewHolder>()  {

    private val items = mutableListOf<Item>()
    private var onItemClickListener: ((Item) -> Unit)? = null

    fun setItems(newItems: List<Item>){
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(listener: (Item) -> Unit) {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRecyclerBinding.inflate(inflater, parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)

        holder.btnDelete.setOnClickListener{
            itemViewModel.deleteItem(item)
            setItems(items.filterNot{it.id == item.id})
        }

        holder.itemView.setOnClickListener {
            val action = FirstFragmentDirections.actionFirstFragmentToEditFragment(item.id)
            it.findNavController().navigate(action)
        }

    }

    override fun getItemCount(): Int = items.size

}