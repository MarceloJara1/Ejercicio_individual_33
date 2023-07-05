package com.example.ejercicioindividual33.Model

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ItemRepository(private val itemDao: ItemDao) {
    val listAllItem: LiveData<List<Item>> = itemDao.getAllItems()

    suspend fun insertItem(item: Item){
        itemDao.insertItem(item)
    }

    suspend fun updateItem(item: Item){
        itemDao.updateItem(item)
    }

    suspend fun deleteAll() {
        withContext(Dispatchers.IO) {
            itemDao.deleteAll()
        }
    }

    fun getItemById(itemId: Int): LiveData<Item> {
        return itemDao.getItemById(itemId)
    }

    suspend fun deleteItem(item: Item){
        itemDao.deleteItem(item)
    }

    suspend fun deleteItemById(itemId: Int){
        itemDao.deleteItemById(itemId)
    }
}