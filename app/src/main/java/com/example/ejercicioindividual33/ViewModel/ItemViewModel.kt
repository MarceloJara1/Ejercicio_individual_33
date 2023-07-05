package com.example.ejercicioindividual33.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.ejercicioindividual33.Model.Item
import com.example.ejercicioindividual33.Model.ItemDb
import com.example.ejercicioindividual33.Model.ItemRepository
import kotlinx.coroutines.launch

class ItemViewModel (application: Application): AndroidViewModel(application){

    private val repository: ItemRepository

    val allItem: LiveData<List<Item>>

    init {
        val itemDao = ItemDb.getDatabase(application).getItemDao()
        repository = ItemRepository(itemDao)
        allItem = repository.listAllItem
    }

    fun insertItem(item: Item) = viewModelScope.launch {
        repository.insertItem(item)
    }

    fun updateItem(item: Item) = viewModelScope.launch {
        repository.updateItem(item)
    }

    fun getItemById(itemId: Int): LiveData<Item> {
        return repository.getItemById(itemId)
    }

    fun deleteAllItem() = viewModelScope.launch {
        repository.deleteAll()
    }

    fun deleteItem(item: Item){
        viewModelScope.launch {
            repository.deleteItem(item)
        }
    }
    fun deleteItemById(itemId:Int){
        viewModelScope.launch {
            repository.deleteItemById(itemId)
        }
    }

}